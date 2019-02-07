<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Práctica #2 - 2014-0056</title>

    <!-- Bootstrap core CSS -->
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/heroic-features.css" rel="stylesheet">

  </head>

  <body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-danger fixed-top">
      <div class="container">
          <a class="navbar-brand" href="/">Práctica #2 - Joshua</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="/">Inicio
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/agregar">Agregar</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Page Content -->
    <div class="container">

      <!-- Page Features -->
        <div class="bg-danger text-center text-white mt-3">
            <#if tamano>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Matricula</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Teléfono</th>
                        <th>Modificar</th>
                        <th>Eliminar</th>
                        <th>Visualizar</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#assign contadorDeEstudiantes = 0>

                        <#list lista as estudiante>
                        <tr class="animated fadeInUp">
                            <td>${estudiante.matricula?string["0"]}</td>
                            <td>${estudiante.nombre}</td>
                            <td>${estudiante.apellido}</td>
                            <td>${estudiante.telefono}</td>
                            <td><a href="/modificar/${contadorDeEstudiantes}" class="btn btn-light" role="button">Modificar</a></td>
                            <td><a href="/eliminar/${contadorDeEstudiantes}"  class="btn btn-light" role="button">Eliminar</a></td>
                            <td><a href="/visualizar/${contadorDeEstudiantes}"  class="btn btn-light" role="button">Visualizar</a></td>
                        </tr>
                            <#assign contadorDeEstudiantes++>
                        </#list>
                    </tbody>
                </table>
            </#if>
        </div>
      <!-- /.row -->

    </div>
    <!-- /.container -->

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>
