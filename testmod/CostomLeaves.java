package testmod;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CostomLeaves extends BlockLeaves implements IMetaName
{
	public static final PropertyEnum<CostomBlockPlanks.EnumType> VARIANT = PropertyEnum.<CostomBlockPlanks.EnumType>create("variant", CostomBlockPlanks.EnumType.class, new Predicate<CostomBlockPlanks.EnumType>()
	{
		public boolean apply(@Nullable CostomBlockPlanks.EnumType apply)
		{
			return apply.getmeta() < 2;
		}
	});
	
	public CostomLeaves(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.WOOD);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, CostomBlockPlanks.EnumType.TUTORIAL).withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
	}
	
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, CostomBlockPlanks.EnumType.byMetadata(meta % 2));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = ((CostomBlockPlanks.EnumType)state.getValue(VARIANT)).getmeta();
		
		if(!((Boolean)state.getValue(DECAYABLE)).booleanValue()) 
		{
			i |= 2;
		}
		
		if(!((Boolean)state.getValue(CHECK_DECAY)).booleanValue())
		{
			i |= 4;
		}
		
		return i;
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for(CostomBlockPlanks.EnumType customblockplanks$enumtype : CostomBlockPlanks.EnumType.values())
		{
			items.add(new ItemStack(this, 1, customblockplanks$enumtype.getmeta()));
		}
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this),1,((CostomBlockPlanks.EnumType)state.getValue(VARIANT)).getmeta());
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return ((CostomBlockPlanks.EnumType)state.getValue(VARIANT)).getmeta();
	}
	
	@Override
	public String getSpecialName(ItemStack stack) {
		return CostomBlockPlanks.EnumType.values()[stack.getItemDamage()].getName();
	}
	
	@Override
	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
		return;
	}
	
	@Override
	protected int getSaplingDropChance(IBlockState state) {
		return 30;
	}
	
	@Override
	public EnumType getWoodType(int meta) {
		return null;
	}
	
	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return NonNullList.withSize(1, new ItemStack(this,1, world.getBlockState(pos).getValue(VARIANT).getmeta()));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {VARIANT,DECAYABLE,CHECK_DECAY});
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

}
