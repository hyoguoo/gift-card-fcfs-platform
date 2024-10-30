package com.hyoguoo.giftcardservice.infrastructure.repository;

import com.hyoguoo.giftcardservice.domain.GiftCardUser;
import com.hyoguoo.giftcardservice.domain.record.GiftCardUserRecord;
import com.hyoguoo.util.ReflectionUtil;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

@Repository
public class FakeGiftCardUserRepositoryImpl implements GiftCardUserRepository {

    private final Map<Long, GiftCardUser> userGiftCardMap = new HashMap<>();
    private Long id = 0L;

    @Override
    public Optional<GiftCardUser> findById(Long id) {
        return Optional.ofNullable(userGiftCardMap.get(id));
    }

    @Override
    public GiftCardUser saveOrUpdate(GiftCardUser giftCardUser) {
        if (giftCardUser.getId() == null) {
            ReflectionUtil.setField(giftCardUser, "id", ++id);
        }
        return userGiftCardMap.put(giftCardUser.getId(), giftCardUser);
    }

    @Override
    public Slice<GiftCardUserRecord> findSliceByUserId(Long userId, Long cursor, Long size) {
        List<GiftCardUser> giftCardUsers = userGiftCardMap.values().stream()
                .filter(giftCardUser -> giftCardUser.getUserId().equals(userId))
                .sorted(Comparator.comparing(GiftCardUser::getId))
                .toList();
        List<GiftCardUserRecord> giftCardUserRecords = giftCardUsers.stream()
                .map(giftCardUser -> GiftCardUserRecord.builder()
                        .userGiftCardId(giftCardUser.getId())
                        .giftCardId(giftCardUser.getGiftCardId())
                        .giftCardName("dummy")
                        .userGiftCardStatus(giftCardUser.getUserGiftCardStatus())
                        .purchaseDate(giftCardUser.getPurchaseDate())
                        .build())
                .collect(Collectors.toList());

        int startIndex = 0;
        if (cursor != null) {
            for (int i = 0; i < giftCardUsers.size(); i++) {
                if (giftCardUsers.get(i).getId().equals(cursor)) {
                    startIndex = i + 1;
                    break;
                }
            }
        }

        int endIndex = Math.min(startIndex + size.intValue(), giftCardUsers.size());
        List<GiftCardUserRecord> giftCardUserRecordSlice = giftCardUserRecords.subList(startIndex, endIndex);

        boolean hasNext = endIndex < giftCardUsers.size();
        return new SliceImpl<>(giftCardUserRecordSlice,
                PageRequest.of(0, size.intValue()),
                hasNext);
    }
}
