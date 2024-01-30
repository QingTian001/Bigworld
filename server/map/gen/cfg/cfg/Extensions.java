package cfg;

public class Extensions {
        public static cfg.time.CompleteDayTime unmarshal_cfg_time_CompleteDayTime(pcore.marshal.Octets os) {
               return new cfg.time.CompleteDayTime(os);
           }     public static cfg.time.MultipleDayRange unmarshal_cfg_time_MultipleDayRange(pcore.marshal.Octets os) {
               return new cfg.time.MultipleDayRange(os);
           }     public static cfg.localize.LocalizeString unmarshal_cfg_localize_LocalizeString(pcore.marshal.Octets os) {
               return new cfg.localize.LocalizeString(os);
           }     public static String[] unmarshal_array_string(pcore.marshal.Octets os) {
               var n = Math.min(os.readSize(), os.size() + 1);
               var x = new String[n];
               for(int i = 0 ; i < n ; i++) {
               x[i] = os.readString();
               }return x;
           }     public static cfg.time.WeekRange unmarshal_cfg_time_WeekRange(pcore.marshal.Octets os) {
               return new cfg.time.WeekRange(os);
           }     public static cfg.time.OneDayRange[] unmarshal_array_cfg_time_OneDayRange(pcore.marshal.Octets os) {
               var n = Math.min(os.readSize(), os.size() + 1);
               var x = new cfg.time.OneDayRange[n];
               for(int i = 0 ; i < n ; i++) {
               x[i] = cfg.Extensions.unmarshal_cfg_time_OneDayRange(os);
               }return x;
           }     public static cfg.login.Server unmarshal_cfg_login_Server(pcore.marshal.Octets os) {
               return new cfg.login.Server(os);
           }     public static cfg.time.OneDayTime unmarshal_cfg_time_OneDayTime(pcore.marshal.Octets os) {
               return new cfg.time.OneDayTime(os);
           }     public static cfg.time.CompleteWeekTime unmarshal_cfg_time_CompleteWeekTime(pcore.marshal.Octets os) {
               return new cfg.time.CompleteWeekTime(os);
           }     public static cfg.time.DayRange[] unmarshal_array_cfg_time_DayRange(pcore.marshal.Octets os) {
               var n = Math.min(os.readSize(), os.size() + 1);
               var x = new cfg.time.DayRange[n];
               for(int i = 0 ; i < n ; i++) {
               x[i] = cfg.Extensions.unmarshal_cfg_time_DayRange(os);
               }return x;
           }     public static cfg.localize.Localization unmarshal_cfg_localize_Localization(pcore.marshal.Octets os) {
               return new cfg.localize.Localization(os);
           }     public static cfg.time.DayRange unmarshal_cfg_time_DayRange(pcore.marshal.Octets os) {
               return new cfg.time.DayRange(os);
           }     public static cfg.time.DayRanges unmarshal_cfg_time_DayRanges(pcore.marshal.Octets os) {
               return new cfg.time.DayRanges(os);
           }     public static cfg.time.DateRange unmarshal_cfg_time_DateRange(pcore.marshal.Octets os) {
               return new cfg.time.DateRange(os);
           }     public static java.util.List<cfg.localize.Localization> unmarshal_list_cfg_localize_Localization(pcore.marshal.Octets os) {
               var n = Math.min(os.readSize(), os.size() + 1);
               java.util.List<cfg.localize.Localization> x = pcore.collection.Factory.newList(n);
               for(int i = 0 ; i < n ; i++) {
               x.add(cfg.Extensions.unmarshal_cfg_localize_Localization(os));
               }return x;
           }     public static cfg.time.TimeRange unmarshal_cfg_time_TimeRange(pcore.marshal.Octets os) {
               var id = os.readInt();
               cfg.time.TimeRange x;
               switch(id) {
               case 0: return null;
               case 8222 : x = new cfg.time.OneDayRange(os); break;case 4025 : x = new cfg.time.WeekRange(os); break;case 11763 : x = new cfg.time.DayRange(os); break;case 14202 : x = new cfg.time.MultipleDayRange(os); break;case 22479 : x = new cfg.time.MultipleWeekRange(os); break;case 60010 : x = new cfg.time.DateRange(os); break;case 58463 : x = new cfg.time.ServerStartTimeRange(os); break;case 6188 : x = new cfg.time.WeekRanges(os); break;case 28687 : x = new cfg.time.DayRanges(os); break;case 58147 : x = new cfg.time.OneDayRanges(os); break;default: throw new pcore.marshal.MarshalException("unknown bean id:" + id);}
               return x;
           }     public static cfg.time.TimeRanges unmarshal_cfg_time_TimeRanges(pcore.marshal.Octets os) {
               var id = os.readInt();
               cfg.time.TimeRanges x;
               switch(id) {
               case 0: return null;
               case 6188 : x = new cfg.time.WeekRanges(os); break;case 28687 : x = new cfg.time.DayRanges(os); break;case 58147 : x = new cfg.time.OneDayRanges(os); break;default: throw new pcore.marshal.MarshalException("unknown bean id:" + id);}
               return x;
           }     public static int[] unmarshal_array_int(pcore.marshal.Octets os) {
               var n = Math.min(os.readSize(), os.size() + 1);
               var x = new int[n];
               for(int i = 0 ; i < n ; i++) {
               x[i] = os.readInt();
               }return x;
           }     public static java.util.List<cfg.login.Server> unmarshal_list_cfg_login_Server(pcore.marshal.Octets os) {
               var n = Math.min(os.readSize(), os.size() + 1);
               java.util.List<cfg.login.Server> x = pcore.collection.Factory.newList(n);
               for(int i = 0 ; i < n ; i++) {
               x.add(cfg.Extensions.unmarshal_cfg_login_Server(os));
               }return x;
           }     public static cfg.time.ServerStartTimeRange unmarshal_cfg_time_ServerStartTimeRange(pcore.marshal.Octets os) {
               return new cfg.time.ServerStartTimeRange(os);
           }     public static cfg.login.LoginConfig unmarshal_cfg_login_LoginConfig(pcore.marshal.Octets os) {
               return new cfg.login.LoginConfig(os);
           }     public static java.util.List<cfg.login.LoginGroup> unmarshal_list_cfg_login_LoginGroup(pcore.marshal.Octets os) {
               var n = Math.min(os.readSize(), os.size() + 1);
               java.util.List<cfg.login.LoginGroup> x = pcore.collection.Factory.newList(n);
               for(int i = 0 ; i < n ; i++) {
               x.add(cfg.Extensions.unmarshal_cfg_login_LoginGroup(os));
               }return x;
           }     public static cfg.time.OneDayRanges unmarshal_cfg_time_OneDayRanges(pcore.marshal.Octets os) {
               return new cfg.time.OneDayRanges(os);
           }     public static cfg.time.WeekTime unmarshal_cfg_time_WeekTime(pcore.marshal.Octets os) {
               return new cfg.time.WeekTime(os);
           }     public static cfg.time.WeekRanges unmarshal_cfg_time_WeekRanges(pcore.marshal.Octets os) {
               return new cfg.time.WeekRanges(os);
           }     public static cfg.time.DayTime unmarshal_cfg_time_DayTime(pcore.marshal.Octets os) {
               return new cfg.time.DayTime(os);
           }     public static cfg.time.MultipleWeekRange unmarshal_cfg_time_MultipleWeekRange(pcore.marshal.Octets os) {
               return new cfg.time.MultipleWeekRange(os);
           }     public static cfg.time.CompleteTime unmarshal_cfg_time_CompleteTime(pcore.marshal.Octets os) {
               var id = os.readInt();
               cfg.time.CompleteTime x;
               switch(id) {
               case 0: return null;
               case 22781 : x = new cfg.time.CompleteDayTime(os); break;case 13939 : x = new cfg.time.CompleteWeekTime(os); break;default: throw new pcore.marshal.MarshalException("unknown bean id:" + id);}
               return x;
           }     public static cfg.time.WeekRange[] unmarshal_array_cfg_time_WeekRange(pcore.marshal.Octets os) {
               var n = Math.min(os.readSize(), os.size() + 1);
               var x = new cfg.time.WeekRange[n];
               for(int i = 0 ; i < n ; i++) {
               x[i] = cfg.Extensions.unmarshal_cfg_time_WeekRange(os);
               }return x;
           }     public static cfg.time.DateTime unmarshal_cfg_time_DateTime(pcore.marshal.Octets os) {
               return new cfg.time.DateTime(os);
           }     public static cfg.time.Time unmarshal_cfg_time_Time(pcore.marshal.Octets os) {
               var id = os.readInt();
               cfg.time.Time x;
               switch(id) {
               case 0: return null;
               case 40407 : x = new cfg.time.DateTime(os); break;case 47942 : x = new cfg.time.OneDayTime(os); break;case 3941 : x = new cfg.time.WeekTime(os); break;case 11888 : x = new cfg.time.DayTime(os); break;case 22781 : x = new cfg.time.CompleteDayTime(os); break;case 13939 : x = new cfg.time.CompleteWeekTime(os); break;default: throw new pcore.marshal.MarshalException("unknown bean id:" + id);}
               return x;
           }     public static cfg.login.LoginGroup unmarshal_cfg_login_LoginGroup(pcore.marshal.Octets os) {
               return new cfg.login.LoginGroup(os);
           }     public static cfg.time.OneDayRange unmarshal_cfg_time_OneDayRange(pcore.marshal.Octets os) {
               return new cfg.time.OneDayRange(os);
           } 
     public static void resolve_cfg_time_CompleteDayTime(cfg.CfgMgr cfgMgr, cfg.time.CompleteDayTime v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_cfg_time_MultipleDayRange(cfg.CfgMgr cfgMgr, cfg.time.MultipleDayRange v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_cfg_localize_LocalizeString(cfg.CfgMgr cfgMgr, cfg.localize.LocalizeString v) {
                    v.resolve(cfgMgr);

           }   public static void resolve_cfg_time_WeekRange(cfg.CfgMgr cfgMgr, cfg.time.WeekRange v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_array_cfg_time_OneDayRange(cfg.CfgMgr cfgMgr, cfg.time.OneDayRange[] v) {
                    for(var _v : v){
                    _v.resolve(cfgMgr);
                    }
           }  public static void resolve_cfg_login_Server(cfg.CfgMgr cfgMgr, cfg.login.Server v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_cfg_time_OneDayTime(cfg.CfgMgr cfgMgr, cfg.time.OneDayTime v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_cfg_time_CompleteWeekTime(cfg.CfgMgr cfgMgr, cfg.time.CompleteWeekTime v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_array_cfg_time_DayRange(cfg.CfgMgr cfgMgr, cfg.time.DayRange[] v) {
                    for(var _v : v){
                    _v.resolve(cfgMgr);
                    }
           }  public static void resolve_cfg_localize_Localization(cfg.CfgMgr cfgMgr, cfg.localize.Localization v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_cfg_time_DayRange(cfg.CfgMgr cfgMgr, cfg.time.DayRange v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_cfg_time_DayRanges(cfg.CfgMgr cfgMgr, cfg.time.DayRanges v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_cfg_time_DateRange(cfg.CfgMgr cfgMgr, cfg.time.DateRange v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_list_cfg_localize_Localization(cfg.CfgMgr cfgMgr, java.util.List<cfg.localize.Localization> v) {
                    for(var _v : v){
                    _v.resolve(cfgMgr);
                    }
           }  public static void resolve_cfg_time_TimeRange(cfg.CfgMgr cfgMgr, cfg.time.TimeRange v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_cfg_time_TimeRanges(cfg.CfgMgr cfgMgr, cfg.time.TimeRanges v) {
                    v.resolve(cfgMgr);

           }   public static void resolve_list_cfg_login_Server(cfg.CfgMgr cfgMgr, java.util.List<cfg.login.Server> v) {
                    for(var _v : v){
                    _v.resolve(cfgMgr);
                    }
           }  public static void resolve_cfg_time_ServerStartTimeRange(cfg.CfgMgr cfgMgr, cfg.time.ServerStartTimeRange v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_cfg_login_LoginConfig(cfg.CfgMgr cfgMgr, cfg.login.LoginConfig v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_list_cfg_login_LoginGroup(cfg.CfgMgr cfgMgr, java.util.List<cfg.login.LoginGroup> v) {
                    for(var _v : v){
                    _v.resolve(cfgMgr);
                    }
           }  public static void resolve_cfg_time_OneDayRanges(cfg.CfgMgr cfgMgr, cfg.time.OneDayRanges v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_cfg_time_WeekTime(cfg.CfgMgr cfgMgr, cfg.time.WeekTime v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_cfg_time_WeekRanges(cfg.CfgMgr cfgMgr, cfg.time.WeekRanges v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_cfg_time_DayTime(cfg.CfgMgr cfgMgr, cfg.time.DayTime v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_cfg_time_MultipleWeekRange(cfg.CfgMgr cfgMgr, cfg.time.MultipleWeekRange v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_cfg_time_CompleteTime(cfg.CfgMgr cfgMgr, cfg.time.CompleteTime v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_array_cfg_time_WeekRange(cfg.CfgMgr cfgMgr, cfg.time.WeekRange[] v) {
                    for(var _v : v){
                    _v.resolve(cfgMgr);
                    }
           }  public static void resolve_cfg_time_DateTime(cfg.CfgMgr cfgMgr, cfg.time.DateTime v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_cfg_time_Time(cfg.CfgMgr cfgMgr, cfg.time.Time v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_cfg_login_LoginGroup(cfg.CfgMgr cfgMgr, cfg.login.LoginGroup v) {
                    v.resolve(cfgMgr);

           }  public static void resolve_cfg_time_OneDayRange(cfg.CfgMgr cfgMgr, cfg.time.OneDayRange v) {
                    v.resolve(cfgMgr);

           } 
}
