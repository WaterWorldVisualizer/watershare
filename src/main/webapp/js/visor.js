  var map,
    mapConfig={
      center: new L.LatLng(41.39, -0.53),//41°39′N 0°53′W
      zoom: 4,
      minZoom:2,
      maxZoom:14,
      zoomControl:false,
      maxBounds: [[-90, -180],[90, 180]]
    },
    heatmapConfig={
          // radius should be small ONLY if scaleRadius is true (or small radius is intended)
          "radius": 2,
          "maxOpacity": .8, 
          // scales the radius based on map zoom
          "scaleRadius": true, 
          // if set to false the heatmap uses the global maximum for colorization
          // if activated: uses the data maximum within the current map boundaries 
          //   (there will always be a red spot with useLocalExtremas true)
          "useLocalExtrema": true,
          // which field name in your data represents the latitude - default "lat"
          latField: 'lat',
          // which field name in your data represents the longitude - default "lng"
          lngField: 'lng',
          // which field name in your data represents the data value - default "value"
          valueField: 'count'
        },
    layers={};




  var mapInit = function(){
    var baseLayer = L.tileLayer(
      'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',{
        attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>',
        maxZoom: 18,
        minZoom:2
      }
    );
    map = new L.Map('map-canvas', mapConfig);
    map.addLayer(baseLayer);
  };

  var loadData = function(properties){
    console.log(properties);
  }

  var loadLayer = function(id, url, color){
    $.ajax({
        type: "GET",
        url: url,
        dataType: 'json',
        success: function (response) {
          console.log(id+' loaded');
          geojsonLayer = L.geoJson(response, {
            style: function(feature) {
                return {color: color};
            },
            pointToLayer: function(feature, latlng) {
                return new L.CircleMarker(latlng, {radius: 3, fillOpacity: 0.85});
            },
            onEachFeature: function (feature, layer) {
                var response='';
                
                console.log(feature.properties);

                for (var key in feature.properties) {
                  if (feature.properties.hasOwnProperty(key) && feature.properties[key]!='') {
                    response+='<strong>'+key+': </strong>'+feature.properties[key]+'<br />';
                  }
                }

                layer.bindPopup(response);
                
                /*layer.on('click', function (e) {
                        loadData(feature.properties);
                    });
                */
            }
          }); //.addTo(map);

          geojsonLayer.heatmap=response.heatmap;

          layers[id]=geojsonLayer;
          showLayer(id);
        }
    });
  };

  var showLayer = function(id){
    //console.log('show '+id);
    map.addLayer(layers[id]);
    hideHeatmap(id);
  };

  var hideLayer = function(id){
    map.removeLayer(layers[id]);
  }

  var showHeatmap = function(id){
    hideLayer(id);
    if(layers[id+'_heatmap']===undefined){
      var heatmapLayer = new HeatmapOverlay(heatmapConfig);
      layers[id+'_heatmap']=heatmapLayer;
      map.addLayer(heatmapLayer);

      var heatmapData={
          max: 8,
          data: layers[id].heatmap
        };
      heatmapLayer.setData(heatmapData);
    }else{
      map.addLayer(layers[id+'_heatmap'],true);
    }
  };

  var hideHeatmap = function(id){
    map.removeLayer(layers[id+'_heatmap']);
  }

/*
    window.onload = function() {

      //mapInit();

      //loadLayer('test', '/test2.json', 'red');

    };
*/