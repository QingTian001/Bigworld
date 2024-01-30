package msg.gs.gm;

public final class SGetModuleList extends pcore.io.Protocol{
    public static final int TYPE_ID = 6099;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public java.util.List<msg.gs.gm.ModuleInfo> modules;

    public SGetModuleList() {
         this.modules = pcore.collection.Factory.newList();

    }

    public SGetModuleList(java.util.List<msg.gs.gm.ModuleInfo> modules ) {
        this.modules = modules;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("modules").append(':').append(msg.Extensions.tostring_list_msg_gs_gm_ModuleInfo(modules)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        msg.Extensions.marshal_list_msg_gs_gm_ModuleInfo(modules,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        modules = msg.Extensions.unmarshal_list_msg_gs_gm_ModuleInfo(os);

    }
}
