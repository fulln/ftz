package me.fulln.ftz.controller;

import me.fulln.ftz.common.GlobalResult;
import me.fulln.ftz.dto.PlayerRankingsDto;
import me.fulln.ftz.enums.GlobalEnums;
import me.fulln.ftz.service.PlayerRankingsService;
import me.fulln.ftz.service.SeasonPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fulln
 * @description 存球员相关信息
 * @date  Created in  16:51  2020-07-22.
 **/
@RestController
@RequestMapping("/snooker")
public class SnookerController {

	@Autowired
	private SeasonPlayerService seasonPlayerService;

	@Autowired
	private PlayerRankingsService playerRankingsService;

	@GetMapping("/initPlayer")
	public GlobalResult initPlayer(){
		return seasonPlayerService.insertToDb();
	}

	@GetMapping("/initRank")
	public GlobalResult initRank(){
		return playerRankingsService.insertToDb();
	}

	@PostMapping("/getList")
	public GlobalResult showList(PlayerRankingsDto dto){
		return playerRankingsService.showList(dto);
	}

	@DeleteMapping("/{id}")
	public GlobalResult showList( @PathVariable Integer id){
		return GlobalResult.results(GlobalEnums.SYS_SUCCESS,playerRankingsService.removeById(id));
	}


}
