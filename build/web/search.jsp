<%-- 
    Document   : search
    Created on : Jun 13, 2025, 8:21:14 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<%@page import ="java.util.List" %>--%>
<%--<%@page import ="kietlna.registration.RegistrationDTO" %>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color ="green">
        Welcome, ${sessionScope.USER_INFOR.fullName}
        </font>
        <h1>Search Page</h1>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" /> <br/>
            <input type="submit" value="Search" name="btAction"/>
        </form> <br/>
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEACH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full Name</th>
                            <th>Role</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                            <tr>
                                <td>
                                    ${counter.count}
                                .</td>
                                <td>${dto.username}</td>
                                <td>${dto.password}</td>
                                <td>${dto.fullName}</td>
                                <td>${dto.role}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
            <c:if test="${empty result}">
                <h2>
                    <font color ="red">
                    No record is matched!!!
                    </font>
                </h2>
            </c:if>
        </c:if>
        <%-- comment....
         <%
             // giong y chang service() ==> _jspService(). 'Don nhan va xu ly yeu cau'
              String searchValue = request.getParameter("txtSearchValue");
              if(searchValue != null){
              List<RegistrationDTO> result = 
             (List<RegistrationDTO>)request.getAttribute("SEACH_RESULT");
             
             if(result!=null){//found
                 %>
                 <table border="1">
                     <thead>
                         <tr>
                             <th>No</th>
                             <th>Username</th>
                             <th>Password</th>
                             <th>Full Name</th>
                             <th>Role</th>
                         </tr>
                     </thead>
                     <tbody>
                         <%
                             int count = 0;
                             for(RegistrationDTO dto : result){
                         %>   
                         <tr>
                             <td>
                                 <%= ++count%>   
                             .</td>
                             <td>
                                 <%= dto.getUsername()%>
                             </td>
                             <td>
                                 <%= dto.getPassword()%>
                             </td>
                             <td>
                                 <%= dto.getFullName()%>
                             </td>
                             <td>
                                 <%= dto.isRole()%>
                             </td>
                         </tr>
                         <%
                             }//traverse dto in result
                         %>
                     </tbody>
                 </table>

        <%
            }else{//not found
                %>
                <h2>
                    <font color="green">
                    No record is matched!!!
                </h2>
        <%
            }
            }//truy cap url vs get method khong co URL?name=value
        %>
        --%>
    </body>
</html>
