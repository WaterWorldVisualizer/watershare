//var SERVICE_URI = "/rest/watershare/";
var SERVICE_URI = "http://localhost:8080/watershare/";

// A $( document ).ready() block.
$( document ).ready(function() {
  mapInit();
  hideSelections();
  initSelector();
  
  //loadLayer('endpoints', SERVICE_URI + 'layer/endpoints', 'green');
  loadLayer('reservoirs', SERVICE_URI + 'layer/reservoirs', 'red');
  loadLayer('water_tanks', SERVICE_URI + 'layer/water_tanks', 'blue');

});

function hideSelections() {
  $( '#heatmap-selected' ).hide();
  $( ':checkbox' ).prop('checked', true);
}

function initSelector() {
  $( '#samples-selected-opt' ).click(function() {
    $( '#heatmap-selected' ).hide();
    $( '#samples-selected' ).show();
  });
  
  $( '#heatmap-selected-opt' ).click(function() {
    $( '#samples-selected' ).hide();    
    $( '#heatmap-selected' ).show();
  });

/*
  $('#samples-selected input').click(function() {
    console.log($(this)[0].value);
    if ($(this).checked){
      showLayer($(this)[0].value);
    }else{
      hideLayer($(this)[0].value);
    }
  });

  $('#heatmap-selected input').click(function() {
    console.log($(this)[0].value);
    if ($(this).checked){
      showHeatmap($(this)[0].value);
    }else{
      hideHeatmap($(this)[0].value);
    }
  });
*/

  $("#samples-selected input[value='reservoirs']").click(function() {
    if ($(this).checked){
      showLayer('reservoirs');
    }else{
      hideLayer('reservoirs');
    }
  });

  $("#samples-selected input[value='water_tanks']").click(function() {
    if ($(this).checked){
      showLayer('water_tanks');
    }else{
      hideLayer('water_tanks');
    }
  });

  $("#heatmap-selected input[value='reservoirs']").click(function() {
    if ($(this).checked){
      showHeatmap('reservoirs');
    }else{
      hideHeatmap('reservoirs');
    }
  });
  $("#heatmap-selected input[value='water_tanks']").click(function() {
    if ($(this).checked){
      showHeatmap('water_tanks');
    }else{
      hideHeatmap('water_tanks');
    }
  });
  /*
  $("#heatmap-selected input[value='reservoirs']").click(function() {
    if ($(this).checked){
      showHeatmap('reservoirs');
    }else{
      hideHeatmap('reservoirs');
    }
  });
*/


}