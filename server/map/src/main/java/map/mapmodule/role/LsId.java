package map.mapmodule.role;

/**
 * Created by zyao on 2020/3/27 10:51
 */
public class LsId {
    private final int linkServerId;
    private final long linkSid;

    public LsId(int linkServerId, long linkSid) {
        this.linkServerId = linkServerId;
        this.linkSid = linkSid;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(linkSid) * 31 + Integer.hashCode(linkServerId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != LsId.class) {
            return false;
        }
        LsId objLsId = (LsId)obj;

        return this.linkServerId == objLsId.linkServerId && this.linkSid == objLsId.linkSid;
    }
}
