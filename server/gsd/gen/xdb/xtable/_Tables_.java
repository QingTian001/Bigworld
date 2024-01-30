package xtable;

final class _Tables_ {
    static {
         pcore.db.PerfectDb.register(xtable.Server.table()); 
         pcore.db.PerfectDb.register(xtable.Users.table()); 
         pcore.db.PerfectDb.register(xtable.AccountUsers.table()); 
         pcore.db.PerfectDb.register(xtable.RoleNameInfos.table()); 
         pcore.db.PerfectDb.register(xtable.RoleInfos.table()); 
         pcore.db.PerfectDb.register(xtable.Test.table()); 

    }
}
