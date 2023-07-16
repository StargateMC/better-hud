package jobicade.betterhud.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class MessageConquestState implements IMessage {
    public int state;
    public String faction;

    public MessageConquestState() {}
    public MessageConquestState(int state, String faction) {
        this.state = state;
        this.faction = faction;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.state = buf.readInt();
        this.faction = ByteBufUtils.readUTF8String(buf); 
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(state);
        ByteBufUtils.writeUTF8String(buf, faction); 
    }
}
