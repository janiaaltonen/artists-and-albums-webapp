<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<title>Web application test page</title>
	<link rel="stylesheet" href="/styles/demo.css">
</head>

<body>

	<h1>🎉 Congratulations! 🎉</h1>

	<p>If you are viewing this page on a browser, several technical
		components appear to work properly:</p>
	<ol>
		<li>First of all, your browser was able to connect to your Tomcat
			server</li>
		<li>Second, the server was able to map your request to the <code>IndexServlet</code>
			class and invoke its <code>doGet</code> method
		</li>
		<li>Third, the <code>doGet</code> method added additional
			information to the request before forwarding it to this JSP file <code>/WEB-INF/index.jsp</code>
			.
		</li>
	</ol>
	<p>
		The added information was set in an attribute
		<code>timeNow</code>
		and its value is:
	</p>
	<p class="time-now">${ timeNow }</p>
	<p>Try refreshing the page and you will see the dynamic value
		changing!</p>
	<div style="background-color: #FFB6C1;">
		<p class="external-css">
			If you see this paragraph with a light green background color, your
			browser was also able to retrieve and external CSS file. The
			background color is defined in a file <a href="/styles/demo.css">/styles/demo.css</a>,
			which was served to you by Tomcat as a static file. If the background
			is red, there was an issue loading the file :(
		</p>
	</div>
	<p>Text may be saved, loaded and transmitted with various different
		encodings. If you see five star emojis here: ⭐️ ⭐️ ⭐️ ⭐️ ⭐️, it means
		that the content was properly served and interpreted with UTF-8
		encoding.</p>
	<hr />

</body>
</html>