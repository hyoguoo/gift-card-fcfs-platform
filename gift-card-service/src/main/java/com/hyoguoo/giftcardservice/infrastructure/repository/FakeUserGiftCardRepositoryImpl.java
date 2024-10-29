package com.hyoguoo.giftcardservice.infrastructure.repository;

import com.hyoguoo.giftcardservice.domain.UserGiftCard;
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
public class FakeUserGiftCardRepositoryImpl implements UserGiftCardRepository {

    private final Map<Long, UserGiftCard> userGiftCardMap = new HashMap<>();
    private Long id = 0L;

    @Override
    public Optional<UserGiftCard> findById(Long id) {
        return Optional.ofNullable(userGiftCardMap.get(id));
    }

    @Override
    public UserGiftCard saveOrUpdate(UserGiftCard userGiftCard) {
        if (userGiftCard.getId() == null) {
            ReflectionUtil.setField(userGiftCard, "id", ++id);
        }
        return userGiftCardMap.put(userGiftCard.getId(), userGiftCard);
    }

    @Override
    public Slice<UserGiftCard> findSliceByUserId(Long userId, Long cursor, Long size) {
        List<UserGiftCard> userGiftCards = userGiftCardMap.values().stream()
                .filter(userGiftCard -> userGiftCard.getUserId().equals(userId))
                .sorted(Comparator.comparing(UserGiftCard::getId)) // ID 순서대로 정렬
                .collect(Collectors.toList());

        int startIndex = 0;
        if (cursor != null) {
            for (int i = 0; i < userGiftCards.size(); i++) {
                if (userGiftCards.get(i).getId().equals(cursor)) {
                    startIndex = i + 1;
                    break;
                }
            }
        }

        int endIndex = Math.min(startIndex + size.intValue(), userGiftCards.size());
        List<UserGiftCard> userGiftCardSlice = userGiftCards.subList(startIndex, endIndex);

        boolean hasNext = endIndex < userGiftCards.size();
        return new SliceImpl<>(userGiftCardSlice,
                PageRequest.of(0, size.intValue()),
                hasNext);
    }
}
