<html>
    <head>
      <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
      <link type="text/css" rel="stylesheet" href="css/projectMain.css"  media="screen,projection"/>    
    </head>

    <body>
        <div id="header" class="z-depth-1">JWP-Trello</div>

        <div id="my_board">My Boards</div>
        <ul id="boards_list">
          <li class="board project_1 waves-effect waves-light btn">First Project</li>
             <div class="add_project not_sortable" >
               <a class="btn-floating btn-large waves-effect waves-light red add_project_btn">
                      <i class="material-icons">add</i>
                </a>
                  <form class="add_project_form">
                    <div class="input-field col s12">
                        <input id="add_project" type="text" class="validate">
                          <label for="list_name">Add a Project...</label></br>
                           <a class="waves-effect waves-light btn save blue-grey lighten-5">save</a>
                          <a class="waves-effect waves-light btn cancel blue-grey lighten-5">cancel</a>
                    </div>
                  </form>
              </div>
        </ul>
 
      <script type="text/javascript" src="lib/jquery-2.2.0.min.js"></script>
      <script type="text/javascript" src="lib/materialize.min.js"></script>
      <script type="text/javascript" src="js/index.js"></script>
    </body>
  </html>