package jobicade.betterhud.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class HUDMessage implements IMessage {
    public String state;
    int id;

    public HUDMessage() {}
    public HUDMessage(int id, String state) {
        this.state = state;
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.id = buf.readInt();
        this.state = ByteBufUtils.readUTF8String(buf); 
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(id);
        ByteBufUtils.writeUTF8String(buf, state); 
    }
}
