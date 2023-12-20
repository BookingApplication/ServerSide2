package ftn.team23.service.interfaces;

import ftn.team23.dto.AccountDataDTO;

public interface IAccountService {

    AccountDataDTO getAccountData(String email);

    AccountDataDTO updateAccountData(AccountDataDTO a);

}
