package everyos.plugin.fsn.customitem.imp;

import dev.mccue.json.Json;
import dev.mccue.json.JsonDecoder;
import dev.mccue.json.JsonEncodable;
import everyos.plugin.fsn.customitem.AbilitySet;
import everyos.plugin.fsn.customitem.CustomItem;
import everyos.plugin.fsn.customitem.CustomItemCreationOptions;
import everyos.plugin.fsn.customitem.CustomItemRarity;
import everyos.plugin.fsn.mcabstract.stats.ItemStats;

public class JSONCustomItemImp implements CustomItem, JsonEncodable {

	private final CustomItemCreationOptions options;

	public JSONCustomItemImp(CustomItemCreationOptions options) {
		this.options = options;
	}

	@Override
	public String getName() {
		return options.name();
	}

	@Override
	public String getMaterialName() {
		return options.materialName();
	}

	@Override
	public String getLore() {
		return options.lore();
	}

	@Override
	public CustomItemRarity getRarity() {
		return options.rarity();
	}

	@Override
	public AbilitySet getAbilities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStats getStats() {
		// TODO Auto-generated method stub
		return null;
	}

	public static JSONCustomItemImp fromJson(Json json) {
		String name = JsonDecoder.field(json, "name", JsonDecoder::string);
		String material = JsonDecoder.field(json, "material", JsonDecoder::string);
		CustomItemRarity rarity = CustomItemRarity.valueOf(JsonDecoder.field(json, "rarity", JsonDecoder::string));
		String lore = JsonDecoder.field(json, "lore", JsonDecoder::string);
		CustomItemCreationOptions options = new CustomItemCreationOptions(name, material, rarity, lore);
		return new JSONCustomItemImp(options);
	}

	@Override
	public Json toJson() {
		return Json.objectBuilder()
			.put("name", options.name())
			.put("material", options.materialName())
			.put("rarity", options.rarity().toString())
			.put("lore", options.lore())
			.build();
	}
	
}
