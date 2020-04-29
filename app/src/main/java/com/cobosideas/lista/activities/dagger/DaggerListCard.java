package com.cobosideas.lista.activities.dagger;

import javax.inject.Inject;

public class DaggerListCard {
    private ListInformation listInformation;

    @Inject
    public DaggerListCard(ListInformation listInformation) {
        this.listInformation = listInformation;
    }
}
