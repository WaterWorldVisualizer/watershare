var SERVICE_URI = "/watershare/layer/";

// A $( document ).ready() block.
$( document ).ready(function() {
  mapInit();
  hideSelections();
  initSelector();
  loadLayer('reservoirs', SERVICE_URI + 'reservoirs', 'red');
  loadLayer('water_tanks', SERVICE_URI + 'water_tanks', 'blue');
  //loadLayer('endpoints', SERVICE_URI + 'endpoints', 'green');
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

}