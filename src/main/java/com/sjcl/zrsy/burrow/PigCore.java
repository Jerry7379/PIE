package com.sjcl.zrsy.burrow;

import java.math.BigInteger;
import java.util.Map;

public interface PigCore {
    BigInteger createPigSale(BigInteger _weight,BigInteger _tokenId,BigInteger _breed);
    Map getPig(BigInteger pigId);
}
