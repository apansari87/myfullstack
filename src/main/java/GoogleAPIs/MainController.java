package GoogleAPIs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/getMyPlace")
public class MainController {
	
	@Autowired
	public GetMyPlaceService getMyPlaceService;

	@RequestMapping(value="/{place}",method = RequestMethod.GET)
	public String getMyPlaces (@PathVariable("place") String type, Model model) {
		model.addAttribute(getMyPlaceService.getTypePlace(type));
		return "/places/view";
	}

}
