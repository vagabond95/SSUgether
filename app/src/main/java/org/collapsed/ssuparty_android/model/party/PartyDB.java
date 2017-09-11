package org.collapsed.ssuparty_android.model.party;


import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.collapsed.ssuparty_android.ui.main.MainPresenter;
import org.collapsed.ssuparty_android.ui.partylist.PartyListPresenter;

public class PartyDB {

    private static final String DB_ALL_PARTY_KEY = "all_party";
    private static final String DB_MY_PARTY_KEY = "my_party";

    private MainPresenter mPresenter;
    private DatabaseReference mRootRef, mAllPartyRef, mMyPartyRef;

    public PartyDB(MainPresenter presenter) {
        mPresenter = presenter;
        initModel();
    }

    public void initModel() {
        this.mRootRef = FirebaseDatabase.getInstance().getReference();
        this.mAllPartyRef = mRootRef.child(DB_ALL_PARTY_KEY);
        this.mMyPartyRef = mRootRef.child(DB_MY_PARTY_KEY);

        this.mAllPartyRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                PartyData partyData = dataSnapshot.getValue(PartyData.class);
                mPresenter.updatePartyList(partyData);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void writeNewParty(PartyData partyData) {
                mAllPartyRef.push().setValue(partyData);
        Log.d("PartyDB",partyData.getTitle());
    }
}
