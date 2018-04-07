<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>SalvageService</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<div class="navcont" align="center">
		<div class="nav">
			<ul>
				<li><a href="index.html">Главная</a></li>
				<li><a href="shiplist">Корабли</a></li>
				<li><a href="icefloelist">Льдины</a></li>
				<li><a href="see">Море</a></li>
				<li><a href="icetop">ТОР</a></li>
			</ul>
		</div>
	</div>
	<main>
	

<table align="center" padding-top="20px">
       <tr class="outtable">
          <th class="outtable">Имя</th>
          <th class="outtable">Фамилия</th>
          <th class="outtable">Спасали</th>
          <th class="outtable">Место нахождения</th>
          <th class="outtable">Ушёл в море</th>
          <th class="outtable">Спасли</th>
          <th class="outtable">Не спасли</th>
       </tr>
       
       <c:forEach items="${listfishermen}" var="fishermen">
          <tr  class="outtable">
             <td class="outtable">${fishermen.name}</td>
             <td class="outtable" align="center">${fishermen.surname}</td>
             <td class="outtable" align="center">${fishermen.numSave} раз</td>
             <td class="outtable" align="center">${fishermen.location}</td>
             <form action="${pageContext.request.contextPath}/editfishermen" method="POST">
             	<td align="center" class="outtable">
             		<input type="hidden" name="name" value="${fishermen.name}"/>
             		<button type="submit" name="button" value="insee">В море</button>
             	</td>
             	<td class="outtable">
             		<input type="hidden" name="name" value="${fishermen.name}"/>
             		<input type="hidden" name="numSave" value="${fishermen.numSave}"/>
             		<button type="submit" name="button" value="save">Спасли</button>
             	</td>
             	<td align="center" class="outtable">
             		<input type="hidden" name="name" value="${fishermen.name}"/>
             		<button type="submit" name="button" value="notsave">Не спасли</button>
             	</td>
			</form>
          </tr>
       </c:forEach>
    </table>
    
    <div align="center">
    <p><p><p><p>Новый рыбак
    </div>
    
    <form action="${pageContext.request.contextPath}/newfishermen" method="POST">
    <table align="center" padding-top="20px">
       <tr class="outtable">
          <th class="outtable">Имя</th>
          <th class="outtable">Фамилия</th>
       </tr>
          	<tr  class="outtable">
             <td class="outtable"><input type="text" name="name" value="${fishermen.name}" /></td>
             <td class="outtable"><input type="text" name="surname" value="${fishermen.surname}" /></td>      
          </tr>
    </table>
    <div align="center">
    <button type="submit" value="button">Зарегистрировать</button>
    </div>
    </form>
    
	</main>

	<footer>
	<hr>
	<p align="center">
		<small>
		<time>01-2017</time> © Kravchenko Pavel
       </small>
	</p>
	</footer>

</body>
</html>