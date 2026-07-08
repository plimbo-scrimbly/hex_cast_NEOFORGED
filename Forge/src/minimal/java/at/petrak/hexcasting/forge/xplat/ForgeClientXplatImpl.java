package at.petrak.hexcasting.forge.xplat;

import at.petrak.hexcasting.api.client.ClientCastingStack;
import at.petrak.hexcasting.common.msgs.IMessage;
import at.petrak.hexcasting.xplat.IClientXplatAbstractions;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
public class ForgeClientXplatImpl implements IClientXplatAbstractions {
    private final ClientCastingStack stack = new ClientCastingStack();

    @Override public void sendPacketToServer(IMessage packet) {}
    @Override public void setRenderLayer(Block block, RenderType type) {}
    @Override public void initPlatformSpecific() {}
    @Override public <T extends Entity> void registerEntityRenderer(EntityType<? extends T> type, EntityRendererProvider<T> renderer) {}
    @Override public void registerItemProperty(Item item, ResourceLocation id, ItemPropertyFunction func) {}
    @Override public ClientCastingStack getClientCastingStack(Player player) { return stack; }
    @Override public void setFilterSave(AbstractTexture texture, boolean filter, boolean mipmap) { texture.setFilter(filter, mipmap); }
    @Override public void restoreLastFilter(AbstractTexture texture) {}
}
