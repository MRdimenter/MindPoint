<#macro login path isRegisterForm> <!-- шаблон с названием login и переменной path-->
    <form action="${path}" method="post">
        <div class="form-group">
            <label for="exampleInputEmail1"> Name </label>
            <input input class="form-control col-sm-3" id="exampleInputEmail1" placeholder="Enter name" type="text"
                   name="username"/>
            <small id="emailHelp" class="form-text text-muted">We'll never share your name with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1"> Password </label>
            <input class="form-control  col-sm-3" id="exampleInputPassword1" placeholder="Password" type="password"
                   name="password"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <#if !isRegisterForm><a href="/registration">Регистрация</a> </#if>
        </div>
        <input type="submit" class="btn btn-primary" value="<#if isRegisterForm>Create<#else>Sign In </#if>"/>
    </form>
</#macro>

<#macro  logout>
    <form action="/logout" method="post">
        <input class="btn btn-outline-primary btn-sm" type="submit" value="Sign Out"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</#macro>