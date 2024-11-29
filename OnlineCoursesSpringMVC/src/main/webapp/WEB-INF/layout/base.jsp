<%-- 
    Document   : base
    Created on : Aug 12, 2024, 11:15:40 AM
    Author     : QuocEzio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <tiles:insertAttribute name="title" />
        </title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.30.1/moment.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <style>
            html, body {
                height: 100%;
                margin: 0;
                display: flex;
                flex-direction: column;
            }
            .container {
                flex: 1;
            }
            footer {
                background-color: #f8f9fa;
                text-align: center;
                padding: 10px 0;
                border-top: 1px solid #dee2e6;
                position: relative;
                width: 100%;
            }
        </style>
    </head>
    <body>
        <!-- HEADER -->
        <tiles:insertAttribute name="header" />
        <div class="container">
            <!-- CONTENT -->
            <tiles:insertAttribute name="content" /> 
        </div>
        <!-- FOOTER -->
        <footer>
            <tiles:insertAttribute name="footer" />
        </footer>
    </body>
</html>

