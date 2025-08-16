#!/usr/bin/env bash
set -euo pipefail

# Usage: ./start-dev.sh [path/to/.env-dev] [profile]
ENV_FILE="${1:-/home/thor/development/.env-dev}"
MVN_PROFILES="${2:-dev}"

# 1) Existenz prüfen
if [ ! -f "$ENV_FILE" ]; then
  echo "ERROR: Env file nicht gefunden: $ENV_FILE" >&2
  exit 1
fi

# 2) Laden (export aller KEY=VALUE)
# .env-dev muss KEY=VALUE Zeilen ohne 'export' enthalten
set -a
# shellcheck disable=SC1090
source "$ENV_FILE"
set +a

# 3) Pflichtvariablen prüfen (DB vars)
required=(DB_USER DB_PASSWORD DB_URL)
missing=0
for key in "${required[@]}"; do
  if [ -z "${!key-}" ]; then
    echo "ERROR: fehlende Variable: $key" >&2
    missing=1
  fi
done
if [ "$missing" -ne 0 ]; then
  echo "ERROR: Bitte .env-dev vervollständigen." >&2
  exit 2
fi

# 4) Wallet-Variable normalisieren: akzeptiere WALLET oder WALLET_PATH
if [ -n "${WALLET_PATH-}" ]; then
  WALLET_DIR="$WALLET_PATH"
elif [ -n "${WALLET-}" ]; then
  WALLET_DIR="$WALLET"
else
  echo "ERROR: fehlende Variable: WALLET or WALLET_PATH" >&2
  exit 2
fi

# 5) Wallet-Ordner prüfen
if [ ! -d "$WALLET_DIR" ]; then
  echo "ERROR: Wallet-Verzeichnis nicht gefunden: $WALLET_DIR" >&2
  exit 3
fi

# 6) Kurzer Sicherheits-Output (kein Passwort ausgeben)
echo "Starting app with:"
echo "  DB_USER     = $DB_USER"
echo "  DB_URL      = ${DB_URL:0:120}$( [ ${#DB_URL} -gt 120 ] && echo '...' )"
echo "  WALLET_PATH = $WALLET_DIR"

# 7) Maven-Start (clean + run)
mvn clean spring-boot:run -Dspring-boot.run.profiles="$MVN_PROFILES"
