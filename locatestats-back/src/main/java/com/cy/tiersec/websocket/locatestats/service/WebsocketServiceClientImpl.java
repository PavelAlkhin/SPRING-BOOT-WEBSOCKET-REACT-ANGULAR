package com.cy.tiersec.websocket.locatestats.service;

import com.cy.tiersec.websocket.locatestats.dto.addmany.request.AddManyRequestDto;
import com.cy.tiersec.websocket.locatestats.dto.addmany.response.*;
import com.cy.tiersec.websocket.locatestats.dto.websocket.CoordinatesDto;
import com.cy.tiersec.websocket.locatestats.dto.websocket.Point;
import org.springframework.stereotype.Service;

@Service
public class WebsocketServiceClientImpl implements WebsocketServiceClient{
    @Override
    public AddManyResponseDto getResponseDto(AddManyRequestDto dto) {
        return AddManyResponseDto.builder()
                .command(0)
                .usrpCfg(UsrpCfg.builder()
                        .rxGain(dto.config.rxGain)
                        .network(dto.config.network)
                        ._2g_params(new _2gParams(dto.config._2g_params.arfcn, dto.config._2g_params.ulsc, dto.config._2g_params.dlsc))
                        ._3g_params(new _3gParams(dto.config._2g_params.arfcn, dto.config._2g_params.ulsc, dto.config._2g_params.dlsc))
                        ._4g_params(new _4gParams(dto.config._2g_params.arfcn, dto.config._2g_params.ulsc, dto.config._2g_params.dlsc))
                        .alpha(dto.config.alpha)
                        .slot(dto.config.slot)
                        .celltrack(dto.config.celltrack)
                        .watcher(dto.config.watcher)
                        .mode(0)
                        .antenna(dto.config.antenna)
                        .gpsSrc(dto.config.gpsSrc)
                        .version(dto.config.version)
                        .app(dto.config.app)
                        .scanUARFCN("missing")
                        .scanPSC(" ")
                        .ulScStart(0)
                        .ulScEnd(0)
                        .scanULSC(" ")
                        .scanTimeouts(" ")
                        .build())
                .build();
    }

    @Override
    public CoordinatesDto getResponseToSocket(AddManyRequestDto dto) {
        return new CoordinatesDto(
                dto.gpsExt.lat,
                dto.gpsExt.lon,
                dto.points.stream().map(p -> new Point(p.sNR, p.rSSI)).toList()
        );
    }
}
