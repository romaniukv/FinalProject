package com.java.project.controller.servlets.transactions;

import com.java.project.model.domain.CreditAccount;
import com.java.project.model.domain.User;
import com.java.project.services.CreditAccountService;
import com.java.project.services.PaymentService;
import com.java.project.services.impl.CreditAccountServiceImpl;
import com.java.project.services.impl.PaymentServiceImpl;
import com.java.project.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/payBill")
public class PayBillServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = AppUtils.getLoginedUser(req.getSession()).getId();
        CreditAccountService creditAccountService = new CreditAccountServiceImpl();
        CreditAccount creditAccount = creditAccountService.selectByUserId(userId);
        if (creditAccount != null) {
            req.setAttribute("creditAccount", creditAccount);
            req.getRequestDispatcher("/views/credit/payBill.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("errorMessage", "Can not pay bill! No credit account found.");
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = AppUtils.getLoginedUser(req.getSession());
        String senderName = user.getFirstName() + " " + user.getLastName();
        String receiverName = req.getParameter("receiverName");
        String purpose = req.getParameter("purpose");

        long senderAccount = Long.valueOf(req.getParameter("senderAccount"));
        long receiverAccount = Long.valueOf(req.getParameter("receiverAccount"));
        BigDecimal amount = BigDecimal.valueOf(Double.valueOf(req.getParameter("amount")));

        PaymentService paymentService = new PaymentServiceImpl();
        if (paymentService.payBill(senderName, senderAccount, receiverName, receiverAccount, amount, purpose)) {
            req.setAttribute("successMessage", "Payment success.");
            req.getRequestDispatcher("/views/successMessage.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "Payment failed.");
            req.getRequestDispatcher("/views/errorMessage.jsp").forward(req, resp);
        }
    }

}
