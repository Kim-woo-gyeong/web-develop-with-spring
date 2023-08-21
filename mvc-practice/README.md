<img src="../mvc-practice/mvc-framework.png" height="400px">

**DispatcherServlet**<br>
: 모든 Request 를 받는 부분.<br>
&ensp;&ensp;&ensp;src/main/java/org/example/mvc<br>
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;ㄴ DispathcerServlet.java

**HandlerMapping**<br>
&ensp;&ensp;&ensp;src/main/java/org/example/mvc<br>
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;ㄴ HandlerMapping.java : interface<br>
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;ㄴ HandlerKey.java : 경로 관련된 model<br>
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;ㄴ RequestMappingHandler.java : 요청경로에 대해 담는 부분.

**HandlerAdapter**<br>
: handler 를 적절한 Controller 를 찾아서 view name 을 응답해줌.<br>
&ensp;&ensp;&ensp;src/main/java/org/example/mvc<br>
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;ㄴ HandlerAdapter.java : interface<br>
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;ㄴ SimpleControllerHandlerAdapter.java : ControllerHandler 인지 체크.<br>
ModelAndView 반환.<br>

**ViewResolver**<br>
: view name을 ModelAndView 객체로 return 시킴.<br>
&ensp;&ensp;&ensp;src/main/java/org/example/mvc/view<br>
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;ㄴ ModelAndView.java : model<br>
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;ㄴ ViewResolver.java : interface<br>
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;ㄴ JspViewResolver.java : "redirect" 인 경우에는 RedirectView 로 return , 그 외의 경우에는 JspView 로 return<br>
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;ㄴ RedirectView.java : response.sendRedirect(...)<br>
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;ㄴ JspView.java : requestDispatcher.forward(...)<br>

<img src="../mvc-practice/mvc-framework-flow.png" height="400px">
