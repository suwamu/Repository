package testmod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class CostomBlockPlanks extends Block implements IMetaName{
	public static final PropertyEnum<CostomBlockPlanks.EnumType> VARIANT = PropertyEnum.<CostomBlockPlanks.EnumType>create("variant",CostomBlockPlanks.EnumType.class);

	public CostomBlockPlanks(String name) {
		super(Material.WOOD);
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.WOOD);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, CostomBlockPlanks.EnumType.TUTORIAL));
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return ((CostomBlockPlanks.EnumType)state.getValue(VARIANT)).getmeta();
	}


	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	{
		for(CostomBlockPlanks.EnumType customblockplanks$enumtype : CostomBlockPlanks.EnumType.values())
		{
			items.add(new ItemStack(this, 1, customblockplanks$enumtype.getmeta()));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, CostomBlockPlanks.EnumType.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((CostomBlockPlanks.EnumType)state.getValue(VARIANT)).getmeta();
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, (int)(getMetaFromState(world.getBlockState(pos))));
	}


	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {VARIANT});
	}






	public static enum EnumType implements IStringSerializable
	{
		TUTORIAL(0,"tutorial"),
		ALUMINIUM(1,"aluminium");

		private static final CostomBlockPlanks.EnumType[] META_LOOKUP = new CostomBlockPlanks.EnumType[values().length];
		private final int meta;
		private final String name, unlocalizedName;

		private EnumType(int meta,String name)
		{
			this(meta,name,name);
		}

		private EnumType(int meta,String name,String unlocalizedName)
		{
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
		}

		@Override
		public String getName() {
			return this.name;
		}
		public int getmeta() {
			return this.meta;
		}

		public String getunlocalizedName() {
			return this.unlocalizedName;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static CostomBlockPlanks.EnumType byMetadata(int meta)
		{
			return META_LOOKUP[meta];
		}

		static
		{
			for(CostomBlockPlanks.EnumType customblockplanks$enumtype : values())
			{
				META_LOOKUP[customblockplanks$enumtype.getmeta()] = customblockplanks$enumtype;
			}
		}


	}


	@Override
	public String getSpecialName(ItemStack stack) {
		return CostomBlockPlanks.EnumType.values()[stack.getItemDamage()].getName();
	}

}
