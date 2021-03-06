package com.java.project.controller.servlets.transactions;

import com.java.project.factory.ServiceFactory;
import com.java.project.model.domain.DepositAccount;
import com.java.project.utils.AppUtils;
import com.java.project.utils.LocalizationUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Servlet used to replenish user's deposit account
 * Forwards to replenish deposit page, takes user's input and then replenish deposit.
 */
@WebServlet("/replenishDeposit")
public class DepositReplenishmentServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(DepositReplenishmentServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = AppUtils.getIdFromRequest(req, resp);

        DepositAccount depositAccount = ServiceFactory.getDepositAccountSrvice().findByKey(id);

        if (depositAccount != null) {
            req.setAttribute("depositAccount", depositAccount);
            req.getRequestDispatcher("/views/deposit/depositReplenishment.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("errorMessage", LocalizationUtils.DEPOSIT_NOT_FOUND);
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long senderAccountNumber = Long.valueOf(req.getParameter("senderAccountNumber"));
        long receiverAccountNumber = Long.valueOf(req.getParameter("receiverAccountNumber"));
        BigDecimal amount = BigDecimal.valueOf(Double.valueOf(req.getParameter("amount")));

        if (ServiceFactory.getDepositReplenishmentService()
                .replenishDeposit(senderAccountNumber, receiverAccountNumber, amount)) {
            logger.info("User " + AppUtils.getLoginedUser(req.getSession()).getUsername()
                    + " replenished deposit account.");
            req.setAttribute("successMessage", LocalizationUtils.TRANSACTION_SUCCESS);
            req.getRequestDispatcher("/views/successMessage.jsp").forward(req, resp);
        } else {
            logger.error("User " + AppUtils.getLoginedUser(req.getSession()).getUsername()
                    + " failed to replenish deposit account.");
            req.setAttribute("errorMessage", LocalizationUtils.CANT_REPLENISH);
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }

}
