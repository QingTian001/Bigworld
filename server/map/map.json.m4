changequote(`[[', `]]')divert(0){
  "serverId": M4_MAP_SERVER_ID,
  "dataDir": "config",
  "log4jFile": "log4j.properties",
  "debug": true,
  "metrics": {
    "port": 0
  },
  "gsServer": {
    "addr": "0.0.0.0",
    "port": M4_MAP_SERVER_ID
  },
  "linkClient" : {
    "servers" :[
      {
        "addr" : "172.18.28.33",
        "port" :30421
      }
    ]
  },
 "kiteServer": {
   "addr": "0.0.0.0",
   "port": M4_MAP_KITE_SERVER_PORT,
   "token": "E2T0I2K#D1N0U8O1R0G"
 }
}