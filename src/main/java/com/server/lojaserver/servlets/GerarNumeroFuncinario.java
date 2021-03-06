/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server.lojaserver.servlets;

import com.google.gson.Gson;
import com.server.lojaserver.beans.CargoBEAN;
import com.server.lojaserver.beans.FuncionarioBEAN;
import com.server.lojaserver.controle.ControleFuncionario;
import com.server.lojaserver.controle.ControleLogin;
import com.server.lojaserver.util.GerarNumeros;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel
 */
@WebServlet(name = "GerarNumero", urlPatterns = {"/loja_server/GerarNumero"}, initParams = {
    @WebInitParam(name = "nomeUsuario", value = ""),
    @WebInitParam(name = "senha", value = "")})
public class GerarNumeroFuncinario extends HttpServlet {

    ControleLogin l = new ControleLogin();
    ControleFuncionario f = new ControleFuncionario();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String n = new String(request.getParameter("nomeUsuario").getBytes("iso-8859-1"), "UTF-8");
        String s = new String(request.getParameter("senha").getBytes("iso-8859-1"), "UTF-8");
        int cod = l.autenticaUsuario(n,s);
        if (cod > 0) {
            response.setHeader("auth", "1");
            boolean para = false;
            int valor = GerarNumeros.geraNumeroInterio(9);
            while (para) {
                if (f.numeroCartaoExistente(valor) == false) {
                    System.out.println("true" + " : " + valor);
                    para = true;

                }
            }
            
            response.setHeader("sucesso", valor + "");

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
