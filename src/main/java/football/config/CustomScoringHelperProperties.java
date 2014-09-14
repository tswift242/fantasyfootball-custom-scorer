package football.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import football.players.modes.Mode;
import football.stats.Rule;
import football.stats.RuleMap;
import football.stats.categories.*;
import football.util.metrics.Metric;
import football.util.metrics.SortOrderMetric;

public final class CustomScoringHelperProperties {

	private static final Logger logger = LoggerFactory.getLogger(CustomScoringHelperProperties.class.getName());

	public static final RuleMap defaultNFLRules = createDefaultNFLRuleMap();

	// properties
	private static boolean useCompositeModel = true;
	private static Mode defaultMode = Mode.QB;
	private static RuleMap defaultRules = defaultNFLRules;
	private static Metric defaultMetric = new SortOrderMetric();
	private static String resultsDirectory = System.getProperty("user.dir") +
				System.getProperty("file.separator") + "fantasyfootball-custom-scorer-results";

	// property keys
	private static final String USE_COMPOSITE_MODEL = "useCompositeModel";
	private static final String DEFAULT_MODE = "defaultMode";
	private static final String DEFAULT_RULES = "defaultRules";
	private static final String RESULTS_DIRECTORY = "resultsDirectory";

	private CustomScoringHelperProperties() {}

	public static void init() {
		logger.info("initializing property values");
		CustomScoringHelperPropertiesLoader loader = CustomScoringHelperPropertiesLoader.getInstance();

		// set properties by getting properties from loader
		setUseCompositeModel(Boolean.parseBoolean(loader.getProperty(USE_COMPOSITE_MODEL)));
		setDefaultMode(Mode.fromString(loader.getProperty(DEFAULT_MODE)));
		setDefaultRules(loader.getProperty(DEFAULT_RULES));
		setResultsDirectory(loader.getProperty(RESULTS_DIRECTORY));
	}

	/*
	* Getters
	*/
	public static boolean useCompositeModel() {
		return useCompositeModel;
	}

	public static Mode getDefaultMode() {
		return defaultMode;
	}

	//TODO: should return defensive copy of this for safety
	public static RuleMap getDefaultRules() {
		return defaultRules;
	}

	//TODO: should return defensive copy of this for safety
	public static Metric getDefaultMetric() {
		return defaultMetric;
	}

	public static String getResultsDirectory() {
		return resultsDirectory;
	}

	public static String getResultsFilename(Mode mode) {
		return (mode.toString() + ".txt");
	}

	/*
	* Setters
	*/
	private static void setUseCompositeModel(boolean createCompositeModel) {
		useCompositeModel = createCompositeModel;
	}

	private static void setDefaultMode(Mode mode) {
		defaultMode = mode;
	}

	private static void setDefaultRules(String site) {
		switch(site) {
			case "NFL":
				defaultRules = defaultNFLRules;
				break;
			case "ESPN":
				logger.warn("Not implemented yet. Using default NFL.com rules");
				break;
			case "Yahoo":
				logger.warn("Not implemented yet. Using default NFL.com rules");
				break;
			case "CBS":
				logger.warn("Not implemented yet. Using default NFL.com rules");
				break;
			default:
				throw new IllegalArgumentException("Invalid site specified: " + site + ". Must be one of these values: NFL, ESPN, Yahaoo, CBS");
		}
	}

	private static void setResultsDirectory(String directory) {
		// allow user to set resultsDirectory property to nothing to indicate that they wish
		// to use the default directory
		if(!StringUtils.isBlank(directory)) {
			resultsDirectory = directory;
		}
	}




	// create and initialize a RuleMap containing the default scoring rules
	// for NFL.com leagues
	private static RuleMap createDefaultNFLRuleMap() {
		RuleMap rules = new RuleMap();
		rules.put(Pass.YDS, new Rule<Pass>(Pass.YDS, 1.0, 25));
		rules.put(Pass.TD, new Rule<Pass>(Pass.TD, 4.0));
		rules.put(Pass.INT, new Rule<Pass>(Pass.INT, -2.0));
		rules.put(Rush.YDS, new Rule<Rush>(Rush.YDS, 1.0, 10));
		rules.put(Rush.TD, new Rule<Rush>(Rush.TD, 6.0));
		rules.put(Rec.YDS, new Rule<Rec>(Rec.YDS, 1.0, 10));
		rules.put(Rec.TD, new Rule<Rec>(Rec.TD, 6.0));
		rules.put(Misc.FUMB_TD, new Rule<Misc>(Misc.FUMB_TD, 6.0));
		rules.put(Misc.FUMB_LOST, new Rule<Misc>(Misc.FUMB_LOST, -2.0));
		rules.put(Misc.TWO_PT_CONV, new Rule<Misc>(Misc.TWO_PT_CONV, 2.0));
		rules.put(Kick.PAT_MD, new Rule<Kick>(Kick.PAT_MD, 1.0));
		rules.put(Kick.FG_MD_0, new Rule<Kick>(Kick.FG_MD_0, 3.0));
		rules.put(Kick.FG_MD_20, new Rule<Kick>(Kick.FG_MD_20, 3.0));
		rules.put(Kick.FG_MD_30, new Rule<Kick>(Kick.FG_MD_30, 3.0));
		rules.put(Kick.FG_MD_40, new Rule<Kick>(Kick.FG_MD_40, 3.0));
		rules.put(Kick.FG_MD_50, new Rule<Kick>(Kick.FG_MD_50, 5.0));
		rules.put(Def.SCK, new Rule<Def>(Def.SCK, 1.0));
		rules.put(Def.INT, new Rule<Def>(Def.INT, 2.0));
		rules.put(Def.FUMB, new Rule<Def>(Def.FUMB, 2.0));
		rules.put(Def.SAF, new Rule<Def>(Def.SAF, 2.0));
		rules.put(Def.TD, new Rule<Def>(Def.TD, 6.0));
		rules.put(Def.RET, new Rule<Def>(Def.RET, 6.0));
		rules.put(Def.PTS, new Rule<Def>(Def.PTS, -3.0, 7));
		return rules;
	}
}
