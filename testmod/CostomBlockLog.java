package testmod;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class CostomBlockLog extends BlockLog implements IMetaName
{
	public static final PropertyEnum<CostomBlockPlanks.EnumType> VARIANT = PropertyEnum.<CostomBlockPlanks.EnumType>create("variant", CostomBlockPlanks.EnumType.class, new Predicate<CostomBlockPlanks.EnumType>()
	{
		public boolean apply(@Nullable CostomBlockPlanks.EnumType apply)
		{
			return apply.getmeta() < 2;
		}
	});

	public CostomBlockLog(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.WOOD);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, CostomBlockPlanks.EnumType.TUTORIAL).withProperty(LOG_AXIS, EnumAxis.Y));
	}


	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for(CostomBlockPlanks.EnumType customblockplanks$enumtype : CostomBlockPlanks.EnumType.values())
		{
			items.add(new ItemStack(this, 1, customblockplanks$enumtype.getmeta()));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.getDefaultState().withProperty(VARIANT, CostomBlockPlanks.EnumType.byMetadata((meta & 1) % 2));

		switch(meta & 6)
		{
		case 0:
			state = state.withProperty(LOG_AXIS, EnumAxis.Y);
			break;
		case 2:
			state = state.withProperty(LOG_AXIS, EnumAxis.X);
			break;
		case 4:
			state = state.withProperty(LOG_AXIS, EnumAxis.Z);
			break;

		default:
			state = state.withProperty(LOG_AXIS, EnumAxis.NONE);
		}

		return state;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public int getMetaFromState(IBlockState state) {
		int i=0;
		i = i | ((CostomBlockPlanks.EnumType)state.getValue(VARIANT)).getmeta();

		switch((BlockLog.EnumAxis)state.getValue(LOG_AXIS))
		{
		case X:
			i |= 2;
			break;
		case Y:
			i |= 4;
			break;
		case Z:
			i |= 6;
			break;
		}
		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this,new IProperty[] {VARIANT,LOG_AXIS});
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


}
