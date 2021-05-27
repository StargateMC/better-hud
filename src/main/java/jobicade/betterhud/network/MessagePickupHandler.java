package jobicade.betterhud.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import jobicade.betterhud.element.HudElement;

public class MessagePickupHandler implements IMessageHandler<MessagePickup, IMessage> {
    @Override
    public IMessage onMessage(MessagePickup message, MessageContext ctx) {
        HudElement.PICKUP.refreshStack(message.getStack());
        return null;
    }
}
