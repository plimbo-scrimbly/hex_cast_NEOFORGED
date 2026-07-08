package at.petrak.hexcasting.forge.xplat;

import at.petrak.hexcasting.api.addldata.*;
import at.petrak.hexcasting.api.casting.ActionRegistryEntry;
import at.petrak.hexcasting.api.casting.arithmetic.Arithmetic;
import at.petrak.hexcasting.api.casting.castables.SpecialHandler;
import at.petrak.hexcasting.api.casting.eval.ResolvedPattern;
import at.petrak.hexcasting.api.casting.eval.sideeffects.EvalSound;
import at.petrak.hexcasting.api.casting.eval.vm.CastingImage;
import at.petrak.hexcasting.api.casting.eval.vm.CastingVM;
import at.petrak.hexcasting.api.casting.eval.vm.ContinuationFrame;
import at.petrak.hexcasting.api.casting.iota.IotaType;
import at.petrak.hexcasting.api.pigment.ColorProvider;
import at.petrak.hexcasting.api.pigment.FrozenPigment;
import at.petrak.hexcasting.api.player.AltioraAbility;
import at.petrak.hexcasting.api.player.FlightAbility;
import at.petrak.hexcasting.api.player.Sentinel;
import at.petrak.hexcasting.common.lib.HexRegistries;
import at.petrak.hexcasting.common.msgs.IMessage;
import at.petrak.hexcasting.interop.pehkui.PehkuiInterop;
import at.petrak.hexcasting.xplat.*;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiFunction;

public class ForgeXplatImpl implements IXplatAbstractions {
    private final Registry<ActionRegistryEntry> actions = new MappedRegistry<>(HexRegistries.ACTION, Lifecycle.stable());
    private final Registry<SpecialHandler.Factory<?>> specials = new MappedRegistry<>(HexRegistries.SPECIAL_HANDLER, Lifecycle.stable());
    private final Registry<IotaType<?>> iotas = new MappedRegistry<>(HexRegistries.IOTA_TYPE, Lifecycle.stable());
    private final Registry<Arithmetic> arithmetics = new MappedRegistry<>(HexRegistries.ARITHMETIC, Lifecycle.stable());
    private final Registry<ContinuationFrame.Type<?>> continuations = new MappedRegistry<>(HexRegistries.CONTINUATION_TYPE, Lifecycle.stable());
    private final Registry<EvalSound> sounds = new MappedRegistry<>(HexRegistries.EVAL_SOUND, Lifecycle.stable());

    @Override public Platform platform() { return Platform.FORGE; }
    @Override public boolean isModPresent(String id) { return false; }
    @Override public boolean isPhysicalClient() { return false; }
    @Override public void initPlatformSpecific() {}
    @Override public void sendPacketToPlayer(ServerPlayer target, IMessage packet) {}
    @Override public void sendPacketNear(Vec3 pos, double radius, ServerLevel dimension, IMessage packet) {}
    @Override public void sendPacketTracking(Entity entity, IMessage packet) {}
    @Override public Packet<ClientGamePacketListener> toVanillaClientboundPacket(IMessage message) { return null; }
    @Override public void setBrainsweepAddlData(Mob mob) {}
    @Override public boolean isBrainswept(Mob mob) { return false; }
    @Override public @Nullable FrozenPigment setPigment(Player target, @Nullable FrozenPigment colorizer) { return colorizer; }
    @Override public void setSentinel(Player target, @Nullable Sentinel sentinel) {}
    @Override public void setFlight(ServerPlayer target, @Nullable FlightAbility flight) {}
    @Override public void setAltiora(Player target, @Nullable AltioraAbility altiora) {}
    @Override public void setStaffcastImage(ServerPlayer target, @Nullable CastingImage image) {}
    @Override public void setPatterns(ServerPlayer target, List<ResolvedPattern> patterns) {}
    @Override public @Nullable FlightAbility getFlight(ServerPlayer player) { return null; }
    @Override public @Nullable AltioraAbility getAltiora(Player player) { return null; }
    @Override public FrozenPigment getPigment(Player player) { return FrozenPigment.DEFAULT.get(); }
    @Override public @Nullable Sentinel getSentinel(Player player) { return null; }
    @Override public CastingVM getStaffcastVM(ServerPlayer player, InteractionHand hand) { return CastingVM.empty(null); }
    @Override public List<ResolvedPattern> getPatternsSavedInUi(ServerPlayer player) { return List.of(); }
    @Override public void clearCastingData(ServerPlayer player) {}
    @Override public @Nullable ADMediaHolder findMediaHolder(ItemStack stack) { return stack.getItem() instanceof ADMediaHolder h ? h : null; }
    @Override public @Nullable ADMediaHolder findMediaHolder(ServerPlayer player) { return null; }
    @Override public @Nullable ADIotaHolder findDataHolder(ItemStack stack) { return stack.getItem() instanceof ADIotaHolder h ? h : null; }
    @Override public @Nullable ADIotaHolder findDataHolder(Entity entity) { return null; }
    @Override public @Nullable ADHexHolder findHexHolder(ItemStack stack) { return stack.getItem() instanceof ADHexHolder h ? h : null; }
    @Override public @Nullable ADVariantItem findVariantHolder(ItemStack stack) { return stack.getItem() instanceof ADVariantItem h ? h : null; }
    @Override public boolean isPigment(ItemStack stack) { return false; }
    @Override public ColorProvider getColorProvider(FrozenPigment pigment) { return pigment.getColorProvider(); }
    @Override public <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(BiFunction<BlockPos, BlockState, T> func, Block... blocks) { return BlockEntityType.Builder.of(func::apply, blocks).build(null); }
    @Override public boolean tryPlaceFluid(Level level, InteractionHand hand, BlockPos pos, Fluid fluid) { return false; }
    @Override public boolean drainAllFluid(Level level, BlockPos pos) { return false; }
    @Override public boolean isCorrectTierForDrops(Tier tier, BlockState bs) { return true; }
    @Override public Ingredient getUnsealedIngredient(ItemStack stack) { return Ingredient.of(stack.getItem()); }
    @Override public IXplatTags tags() { return new IXplatTags() {
        @Override public TagKey<Item> amethystDust() { return ItemTags.create(at.petrak.hexcasting.api.HexAPI.modLoc("amethyst_dust")); }
        @Override public TagKey<Item> gems() { return ItemTags.create(at.petrak.hexcasting.api.HexAPI.modLoc("gems")); }
    }; }
    @Override public LootItemCondition.Builder isShearsCondition() { return null; }
    @Override public String getModName(String namespace) { return namespace; }
    @Override public Registry<ActionRegistryEntry> getActionRegistry() { return actions; }
    @Override public Registry<SpecialHandler.Factory<?>> getSpecialHandlerRegistry() { return specials; }
    @Override public Registry<IotaType<?>> getIotaTypeRegistry() { return iotas; }
    @Override public Registry<Arithmetic> getArithmeticRegistry() { return arithmetics; }
    @Override public Registry<ContinuationFrame.Type<?>> getContinuationTypeRegistry() { return continuations; }
    @Override public Registry<EvalSound> getEvalSoundRegistry() { return sounds; }
    @Override public boolean isBreakingAllowed(ServerLevel world, BlockPos pos, BlockState state, @Nullable Player player) { return true; }
    @Override public boolean isPlacingAllowed(ServerLevel world, BlockPos pos, ItemStack blockStack, @Nullable Player player) { return true; }
    @Override public PehkuiInterop.ApiAbstraction getPehkuiApi() { return new PehkuiInterop.ApiAbstraction() {
        @Override public float getScale(Entity e) { return 1f; }
        @Override public void setScale(Entity e, float scale) {}
    }; }
}
