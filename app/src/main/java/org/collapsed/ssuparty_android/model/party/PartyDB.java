package org.collapsed.ssuparty_android.model.party;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PartyDB {

    private static final String DB_ALL_PARTY_KEY = "allParty";

    private static OnPartyDataFetchedListener mPresenter;
    private static DatabaseReference mRootRef, mAllPartyRef;

    public static void fetchAllParty(OnPartyDataFetchedListener listener) {
        mPresenter = listener;
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAllPartyRef = mRootRef.child(DB_ALL_PARTY_KEY);
        mAllPartyRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                PartyData partyData = dataSnapshot.getValue(PartyData.class);
                mPresenter.onFetched(partyData);
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

    public static void writeNewParty(PartyData partyData) {
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAllPartyRef = mRootRef.child(DB_ALL_PARTY_KEY);
        String partyKey = mAllPartyRef.push().getKey();
        partyData.setPartyID(partyKey);
        mAllPartyRef.child(partyKey).setValue(partyData);
    }

    public static void executeFetch(OnPartyDataFetchedListener listener, String uid) {
        mPresenter = listener;
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAllPartyRef = mRootRef.child(DB_ALL_PARTY_KEY);
        mAllPartyRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                boolean isAdded = false;
                PartyData partyData = dataSnapshot.getValue(PartyData.class);
                if (partyData.getParticipants() != null) {
                    for (String fetchedUid : partyData.getParticipants()) {
                        if (fetchedUid.equals(uid)) {
                            isAdded = true;
                            mPresenter.onFetched(partyData);
                        }
                    }
                }

                if (!isAdded && partyData.getFounder().equals(uid)) {
                    mPresenter.onFetched(partyData);
                }
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

    public interface OnPartyDataFetchedListener {
        void onFetched(PartyData data);
    }
}
