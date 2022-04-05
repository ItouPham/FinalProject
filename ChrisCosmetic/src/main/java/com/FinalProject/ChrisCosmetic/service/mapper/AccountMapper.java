package com.FinalProject.ChrisCosmetic.service.mapper;

import org.mapstruct.Mapper;

import com.FinalProject.ChrisCosmetic.dto.AccountDTO;
import com.FinalProject.ChrisCosmetic.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper extends EntityMapper<AccountDTO, Account> {

}
