package football.core;

import football.core.intface.CustomScoringHelperController;
import football.core.intface.CustomScoringHelperModel;
import football.core.intface.CustomScoringHelperView;
import football.core.listeners.ModeListener;
import football.core.listeners.LeagueSiteListener;
import football.core.listeners.RecalculateScoreListener;
import football.core.listeners.RestoreDefaultRulesListener;
import football.core.listeners.WindowCloseListener;

public final class SimpleController implements CustomScoringHelperController
{
	public SimpleController(CustomScoringHelperModel model,
                            CustomScoringHelperView view) {
		registerListeners(model, view);
	}

	// add listeners to the view
	@Override
	public void registerListeners(CustomScoringHelperModel model,
                            CustomScoringHelperView view) {
		view.addModeListener(new ModeListener(model, view));
		view.addLeagueSiteListener(new LeagueSiteListener(model, view));
		// using RecalculateScoreListener for this instead
		//view.addRulesListener(new RulesListener(model));
		view.addRecalculateScoreListener(new RecalculateScoreListener(model, view));
		view.addRestoreDefaultRulesListener(new RestoreDefaultRulesListener(model));
		view.addWindowCloseListener(new WindowCloseListener(model));
	}
}
