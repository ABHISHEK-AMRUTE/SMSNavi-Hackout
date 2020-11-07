const request  =require("request")
const geocode =(address,callback)=>
{  var url = "https://api.mapbox.com/geocoding/v5/mapbox.places/"+ encodeURIComponent(address)+".json?access_token=pk.eyJ1IjoiYWJoaXNoZWs5OTkiLCJhIjoiY2s3Z200dDhsMDEyZTNrcWRrZHpyanlrbCJ9.VP-Hevh7jsp0apFn3Y8KDA"
    request({url:url,json:true},(error,response={})=>
    {
         if(error)
         {  callback("Unabble to get data",undefined);

         }
         else
         {    var data
              try{
                data ={
                    longitute:response.body.features[0].center[0],
                    latitude:response.body.features[0].center[1]
                      }
                      callback(undefined,data);
              }
              catch(e)
              {
               callback({error:'Try another name'},undefined);
              }
              
               
         }
    });
}
module.exports = geocode