package msg.gs.gm;

public final class ModuleInfo  implements pcore.marshal.IMarshal {

    public static final int TYPE_ID = 0;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public String name;
    public String desc;
    public java.util.List<msg.gs.gm.CommandInfo> commands;

    public ModuleInfo() {
         this.name = "";
         this.desc = "";
         this.commands = pcore.collection.Factory.newList();

    }

    public ModuleInfo(String name , String desc , java.util.List<msg.gs.gm.CommandInfo> commands ) {
        this.name = name;
         this.desc = desc;
         this.commands = commands;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("name").append(':').append(name).append(',');
        _sb_.append("desc").append(':').append(desc).append(',');
        _sb_.append("commands").append(':').append(msg.Extensions.tostring_list_msg_gs_gm_CommandInfo(commands)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeString(name);
        os.writeString(desc);
        msg.Extensions.marshal_list_msg_gs_gm_CommandInfo(commands,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        name = os.readString();
        desc = os.readString();
        commands = msg.Extensions.unmarshal_list_msg_gs_gm_CommandInfo(os);

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(3);
        os.writeCompactUint(pcore.marshal.Tag.STRING | (31320 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(name);  

        os.writeCompactUint(pcore.marshal.Tag.STRING | (65123 << pcore.marshal.Tag.TAG_SHIFT));  os.writeString(desc);  

        os.writeCompactUint(pcore.marshal.Tag.LIST | (53647 << pcore.marshal.Tag.TAG_SHIFT));  { var _oldOs = os; os = new pcore.marshal.Octets(); msg.Extensions.marshal_compatible_list_msg_gs_gm_CommandInfo(commands,os); _oldOs.writeOctets(os); os = _oldOs;}  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.STRING | (31320 << pcore.marshal.Tag.TAG_SHIFT)):   name = os.readString(); break;  
                 case (pcore.marshal.Tag.STRING | (65123 << pcore.marshal.Tag.TAG_SHIFT)):   desc = os.readString(); break;  
                 case (pcore.marshal.Tag.LIST | (53647 << pcore.marshal.Tag.TAG_SHIFT)):  { var _oldOs = os; os = os.readOctets(); commands = msg.Extensions.unmarshal_compatible_list_msg_gs_gm_CommandInfo(os); os = _oldOs; break; }   
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
