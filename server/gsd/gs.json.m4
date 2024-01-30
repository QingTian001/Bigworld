changequote(`[[', `]]')divert(0){
  "serverId": M4_GS_SERVER_ID,
  "regionId": 1,
  "dataDir": "config",
  "log4jFile": "log4j.properties",
  "debug": true,
  "rechargeDebug": true,
  "loginDebug": true,
  "sensitiveWordsFile" : "shield_name.txt",
  "timeZone" : "UTC",
   "metrics" : {
    "port":0
  },
  "linkClient" : {
    "userExpireTime":60,
    "servers" :[
      {
        "addr" : "172.18.28.33",
        "port" :30421
      }
    ]
  },
  "mapClient" : {
    "servers" :[
      {
        "lineId" : 1,
        "addr" : "172.18.28.33",
        "port" : M4_MAP_SERVER_ID
      }
    ]
  },
  "gm" : {
     "gmPort":10001,
     "isTest":true
  },

  "db": {
    "localId" : M4_LOCALID,
    "checkpointPeriod": 10,
    "shrinkPeriod": 30,
    "defaultRecordExpireTime": 300,
    "defaultCacheRecordNum": 10000,
    "backupPeriod" : "3600",

    "tables": [
      {
        "name": "roleinfos",
        "maxCacheRecordNum": 10000,
        "recordExpireTime" : 1800
      }
    ],
    "storage" : {
      "type": "pdb",
      "dbHome" : "gs.db",
      "libHome" : "libs",
      "backupHome" : "_backup_"
    },
   "slaveStorage" : {
      "open":M4_SLAVESTORAGE_ISOPEN,
      "type" : "mysql;loget",
      "userName" : "mcw_user",
      "password" : "mcw_user@123",
      "url": M4_SLAVESTORAGE_URL,
      "initialSize" : 10,
      "maxTotal" : 50,
      "maxIdle" : 60,
      "maxWaitMillis":10000,
      "minIdle" : 30
    }
  },
  "serverList" : {
      "synchServerRoleNumIntervalInMillis": 5000,
      "servers" :[
        {
          "addr" : "172.18.28.33",
          "port" :9101
        }
      ]
    },
  "aud" : {
    "keepAliveInterval": 30,
    "server" :
    {
      "addr" : "172.18.28.33",
      "port" :8093
    }
  },
  "matchConfig" : {
    "servers" :[
      {
        "addr" : "127.0.0.1",
        "port" : 61010
      }
    ]
  },
  "crossConfig" : {
    "open":false,
    "servers" :[
      {
        "addr" : "172.18.28.33",
        "port" : 51010
      }
    ]
  },
  "global" : {
   "server" :
   {
     "addr" : "172.18.28.33",
     "port" :8094
   }
  }, 
  "kiteServer": {
    "addr": "0.0.0.0",
    "port": M4_GS_KITE_SERVER_PORT,
    "token": "E2T0I2K#D1N0U8O1R0G"
  },
  "onemtSdk" : {
       "appId":"100010022",
       "appKey":"0be5ded77827aab2dc369f80086f1b23",
       "timeOutInMillis":5000,
       "pushSite":"http://apipushbeta.menaapp.net",
       "translateSite":"http://transbeta.onemt.co",
       "iMSite":"http://apiimbeta.menaapp.net",
       "bUsingFeeTranslate": false,
       "serverIcon":"city.png",
       "gmUrl":"http://10.0.0.223:8800",
       "host":"http://iapdev.menaapp.net",
       "aesKey":"kEYLTHcjdaxMAeiG",
       "aesIV":"5Ys3yVIHDih0S89G"
     }
}
