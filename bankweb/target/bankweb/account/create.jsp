<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
        
        <!-- form create account -->
    <div class="container col-lg-6 mb-5 mb-lg-0 bg-light mt-5">
        <form class="row g-3" action="accountcontroller" method="post">
        <div class="col-md-6">
            <label for="inputAccountDni" class="form-label">Dni</label>
            <input type="text" class="form-control" id="inputAccountDni" name="accountCustomerDni">
        </div>
        <div class="col-md-6">
            <label for="inputBalance" class="form-label">Saldo</label>
            <input type="number" class="form-control" id="inputBalance" name="accountBalance" step="any" >
        </div>
        <div class="col-md-6">
            <label for="inputActive" class="form-label">Tipo de Cuenta</label>
            <select id="inputActive" class="form-select" name ="selectActive">
                <option value = "0" selected>Seleccione estado de cuenta</option>
                <option value = "1">Activa</option>
                <option value = "2">Inactiva</option>
            </select>
        </div>
        <div class="col-md-6">
            <label for="inputAccountType" class="form-label">Tipo de Cuenta</label>
            <select id="inputAccountType" class="form-select" name ="selectTypeAccount">
                <option value = "0" selected>Seleccione tipo</option>
                <option value = "2">Ahorro</option>
                <option value = "3">Corriente</option>
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