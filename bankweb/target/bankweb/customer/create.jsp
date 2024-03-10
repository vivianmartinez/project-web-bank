<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <!-- form create customer -->
    <div class="container col-lg-6 mb-5 mb-lg-0 bg-light mt-5">
        <form class="row g-3" action="ControllerServlet" method="post">
        <div class="col-md-6">
            <label for="inputName" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="inputName">
        </div>
        <div class="col-md-6">
            <label for="inputLastName" class="form-label">Apellidos</label>
            <input type="text" class="form-control" id="inputLastName">
        </div>
        <div class="col-md-6">
            <label for="inputDni" class="form-label">Dni</label>
            <input type="text" class="form-control" id="inputDni">
        </div>
        <div class="col-md-6">
            <label for="inputDateBirth" class="form-label">Fecha Nacimiento</label>
            <input type="date" class="form-control" id="inputDateBirth">
        </div>
        <div class="col-md-6">
            <label for="inputCity" class="form-label">Ciudad</label>
            <input type="text" class="form-control" id="inputCity">
        </div>
        <div class="col-md-6">
            <label for="inputEmail" class="form-label">Email</label>
            <input type="email" class="form-control" id="inputEmail">
        </div>
        <div class="col-md-6">
            <label for="inputPassword" class="form-label">Password</label>
            <input type="password" class="form-control" id="inputPassword">
        </div>
        <div class="col-md-4">
            <label for="inputAccountType" class="form-label">Tipo de Cuenta</label>
            <select id="inputAccountType" class="form-select" name ="selectTypeAccount">
            <option selected>Seleccione tipo</option>
                <option value = "1">Ahorro</option>
                <option value = "2">Corriente</option>

            </select>
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary">Guardar</button>
        </div>
        </form>
    </div>      
    <%@include file="../endcontent.jsp" %>
</main>
<%@include file="../footer.jsp" %> 