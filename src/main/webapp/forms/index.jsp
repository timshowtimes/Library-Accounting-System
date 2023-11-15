<html>
<body>
    <h2>Welcome to the library accounting system!</h2>
    <br/>
    <form style="margin-bottom: 10px" method="GET" action="${pageContext.request.contextPath}/people">
        <input type="submit" value="Clients Database">
    </form>
    <form method="GET" action="${pageContext.request.contextPath}/books">
        <input type="submit" value="Library Database">
    </form>
</body>
</html>
