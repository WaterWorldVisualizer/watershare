var SERVICE_URI = "/rest/watershare/";
//var SERVICE_URI = "http://localhost:8080/";

// A $( document ).ready() block.
$( document ).ready(function() {
  mapInit();
  hideSelections();
  initSelector();
  
  loadLayer('endpoints', SERVICE_URI + 'layer/endpoints', 'green');
  loadLayer('reservoirs', SERVICE_URI + 'layer/reservoirs', 'red');
  loadLayer('water_tanks', SERVICE_URI + 'layer/water_tanks', 'blue');
  /*loadLayer('endpoints', 'resources/endpoints_data.json', 'green');
   loadLayer('reservoirs', 'resources/reservoir_water_data.json', 'red');
   loadLayer('water_tanks', 'resources/tanks_water_data.json', 'blue');*/

});

function hideSelections() {
  $( '#heatmap-selected' ).hide();
  $( ':checkbox' ).prop('checked', true);
}

function initSelector() {
  $( '#samples-selected-opt' ).click(function() {
    $( '#dropdownMenu1').html('Located Samples');
    $( '#heatmap-selected' ).hide();
    $( '#samples-selected' ).show();
  });
  
  $( '#heatmap-selected-opt' ).click(function() {
    $( '#dropdownMenu1').html('Heatmap');
    $( '#samples-selected' ).hide();    
    $( '#heatmap-selected' ).show();
    hideLayer('reservoirs');
    hideLayer('water_tanks');
    hideLayer('endpoints');
    showHeatmap('endpoints');
  });



  $("#samples-selected input[value='reservoirs']").click(function() {
    console.log($(this));
    if ($(this)[0].checked){
      showLayer('reservoirs');
    }else{
      hideLayer('reservoirs');
    }
  });

  $("#samples-selected input[value='water_tanks']").click(function() {
    if ($(this)[0].checked){
      showLayer('water_tanks');
    }else{
      hideLayer('water_tanks');
    }
  });
  $("#samples-selected input[value='endpoints']").click(function() {
    if ($(this)[0].checked){
      showLayer('endpoints');
    }else{
      hideLayer('endpoints');
    }
  });

  $("#heatmap-selected input[value='reservoirs']").click(function() {
    if ($(this)[0].checked){
      hideHeatmap('water_tanks');
      hideHeatmap('endpoints');
      showHeatmap('reservoirs');
    }else{
      hideHeatmap('reservoirs');
    }
  });
  $("#heatmap-selected input[value='water_tanks']").click(function() {
    if ($(this)[0].checked){
      hideHeatmap('reservoirs');
      hideHeatmap('endpoints');
      showHeatmap('water_tanks');

    }else{
      hideHeatmap('water_tanks');
    }
  });

  $("#heatmap-selected input[value='endpoints']").click(function() {
    if ($(this)[0].checked){
      hideHeatmap('reservoirs');
      hideHeatmap('water_tanks');
      showHeatmap('endpoints');
    }else{
      hideHeatmap('endpoints');
    }
  });


}