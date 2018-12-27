




<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>




<script type="text/javascript">

    $document.ready(function () {
        document.f.username.focus();
    });
</script>










    <div class="login-form__pwd_control">
        <form class="login-form" name="Login" action='${pageContext.request.contextPath}/login' method='POST' autocomplete="off">
            <c:if test="${param.error != null}">
                <p class="loginerror"> *Please check your credentials</p>
            </c:if>

            <label class="login-form__label">Soeid
                <input type="text" id="username" name="username" class="login-form__input login-form__input--login" placeholder="soeid">
            </label>

            <label class="login-form__label">Password
                <input type="password" id="password" name="password" class="login-form__input login-form__input--password" placeholder="password"></label>
            <div id="error" class="login-form__error"></div>
            <div class="login-form__loginsso">
                    <%--<img class="login-form_loginsso--ssoicon" src="sso-login/static/img/sso.svg">--%>
                    <%--<div class="login-forom_loginsso-ssotext">Single Sign-on</div>--%>

                <div class="form-group text-center">
                    <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                    <label for="remember" > Remember Me</label>
                </div>



                <input class="login-form__loginsso--login" type="submit" value="Login">
            </div>

            <input name="_csrf" type="hidden" value="${_csrf.token}"/>
        </form>

        <div class="pwd_controls">
           <%-- <div class="pwd_controls__change">
                <a href="#" id="passChangeLink" target="_blank">Change password</a>
            </div>--%>
            <div class="pwd_controls__reset">
                <a href="${pageContext.request.contextPath}/forgot" id="oneResetLink" target="_blank">Forgot Password</a>
            </div>
            <div class="pwd_controls__newaccess">
                <a href="${pageContext.request.contextPath}/newaccount" id="requestAccessLink" target="_blank">Request New Access</a>

            </div>


        </div>

</div>



