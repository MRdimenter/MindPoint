<#import "parts/common.ftl" as c>

<@c.page>

    <h5>${username}</h5>

    ${message?ifExists}

    <form method="post">


        <div class="form-group">
            <label for="exampleInputPassword1">Email</label>
            <input class="form-control  col-sm-3" id="exampleInputPassword1" placeholder="example@some.com"
                   type="email" value="${email!''}"
                   name="email"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </div>

        <div class="form-group">
            <label for="exampleInputPassword1"> Password </label>
            <input class="form-control  col-sm-3" id="exampleInputPassword1" placeholder="Password" type="password"
                   name="password"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </div>
        <div>

        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" class="btn btn-primary mt-2" value="save"/>
    </form>




</@c.page>
