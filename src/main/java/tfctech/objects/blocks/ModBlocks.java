package tfctech.objects.blocks;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import net.dries007.tfc.objects.items.itemblock.ItemBlockTFC;
import tfctech.objects.blocks.devices.BlockElectricForge;
import tfctech.objects.tileentities.TEElectricForge;

import static net.dries007.tfc.objects.CreativeTabsTFC.CT_MISC;
import static net.dries007.tfc.util.Helpers.getNull;
import static tfctech.TFCTech.MODID;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = MODID)
@GameRegistry.ObjectHolder(MODID)
public final class ModBlocks
{
    public static final BlockElectricForge ELECTRIC_FORGE = getNull();

    private static ImmutableList<ItemBlock> allInventoryItemBlocks;
    //todo latex
    //todo recipe for rubber akin to vulcanization process(look for TFCTech)

    public static ImmutableList<ItemBlock> getAllInventoryItemBlocks()
    {
        return allInventoryItemBlocks;
    }


    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> r = event.getRegistry();
        ImmutableList.Builder<ItemBlock> inventoryItemBlocks = ImmutableList.builder();

        inventoryItemBlocks.add(new ItemBlockTFC(register(r, "electric_forge", new BlockElectricForge(), CT_MISC)));

        allInventoryItemBlocks = inventoryItemBlocks.build();

        //Register TEs
        register(TEElectricForge.class, "electric_forge");
    }

    private static <T extends Block> T register(IForgeRegistry<Block> r, String name, T block, CreativeTabs ct)
    {
        block.setCreativeTab(ct);
        return register(r, name, block);
    }

    private static <T extends Block> T register(IForgeRegistry<Block> r, String name, T block)
    {
        block.setRegistryName(MODID, name);
        block.setTranslationKey(MODID + "." + name.replace('/', '.'));
        r.register(block);
        return block;
    }

    private static <T extends TileEntity> void register(Class<T> te, String name)
    {
        TileEntity.register(MODID + ":" + name, te);
    }
}
