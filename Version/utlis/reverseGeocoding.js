const request  =require("request")
const reverseGeocode =(longitute,latitude,callback)=>
{  
    var url = "https://api.mapbox.com/geocoding/v5/mapbox.places/"+longitute+","+latitude+".json?access_token=pk.eyJ1IjoiYWJoaXNoZWs5OTkiLCJhIjoiY2s3Z200dDhsMDEyZTNrcWRrZHpyanlrbCJ9.VP-Hevh7jsp0apFn3Y8KDA"
    request({url:url,json:true},(error,response={})=>
    {
         if(error)
         {  
           console.log(error)    
           callback("Unabble to get data",undefined);

         }
         else
         {    var data
              try{
                      data ={
                         place_name:response.body.features[0].place_name,
                         text:response.body.features[0].text
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
module.exports = reverseGeocode