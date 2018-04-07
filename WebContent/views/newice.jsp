<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Create Product</title>
   </head>
   <body>
    
      
      <h3>Create Product</h3>
       
      <p style="color: red;">${errorString}</p>
       
      <form action="${pageContext.request.contextPath}/newice" method="POST">
         <table border="0">
            <tr>
               <td>Название</td>
               <td><input type="text" name="name" value="${iceFloe.name}" /></td>
            </tr>
            <tr>
               <td>Размер, М2</td>
               <td><input type="text" name="size" value="${iceFloe.size}" /></td>
            </tr>
            <tr>
               <td>Количество рыбаков</td>
               <td><input type="text" name="numberOfFishermen" value="${iceFloe.numberOfFishermen}" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <button type="submit" value="button">Добавить</button>
                   <a href="icefloelist">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
       
   </body>
</html>