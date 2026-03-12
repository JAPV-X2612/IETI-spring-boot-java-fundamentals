const request = require("supertest");
const server = require("../app.js");

const premiumClients = [
  { nombre: "Roberto", age: "23" },
  { nombre: "Pablo", age: "22" },
  { nombre: "Laura", age: "22" },
  { nombre: "Sofia", age: "21" },
  { nombre: "Angelica", age: "24" },
  { nombre: "Estiven", age: "27" },
  { nombre: "Armando", age: "28" },
];

test("express install is correct", () => {
  const express = require("express");
  expect(express).not.toBeUndefined();
});

test("server run correct", async () => {
  const response = await request(server).get("/");
  expect(response.status).toBe(200);
});

test("get method for premium-clients on the ada cars rest API is correct", async () => {
  const response = await request(server).get("/api/premium-clients");
  clients = JSON.parse(response.text).clients;
  expect(clients).toEqual(premiumClients);
});

test("post method for premium-clients on the ada cars rest API is correct", async () => {
  const response = await request(server)
    .post("/api/premium-clients")
    .send({ nombre: "Jorge", age: "25" });
  client = JSON.parse(response.text).createdClient;
  expect(client).toEqual({ nombre: "Jorge", age: "25" });
});

test("put method for premium-clients on the ada cars rest API is correct", async () => {
  await request(server)
    .put("/api/premium-clients/3")
    .send({ nombre: "Jorge", age: "25" });

  const requestValidation = await request(server).get("/api/premium-clients");
  clients = JSON.parse(requestValidation.text).clients;
  expect(clients[3]).toEqual({ nombre: "Jorge", age: "25" });
});

test("delete method for premium-clients on the ada cars rest API is correct", async () => {
  const response = await request(server).delete("/api/premium-clients/4");
  client = JSON.parse(response.text).deletedClient;
  expect(client).toEqual(premiumClients[4]);
});
