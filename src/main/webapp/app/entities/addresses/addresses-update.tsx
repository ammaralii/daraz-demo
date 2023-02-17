import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDarazUsers } from 'app/shared/model/daraz-users.model';
import { getEntities as getDarazUsers } from 'app/entities/daraz-users/daraz-users.reducer';
import { IAddresses } from 'app/shared/model/addresses.model';
import { getEntity, updateEntity, createEntity, reset } from './addresses.reducer';

export const AddressesUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const darazUsers = useAppSelector(state => state.darazUsers.entities);
  const addressesEntity = useAppSelector(state => state.addresses.entity);
  const loading = useAppSelector(state => state.addresses.loading);
  const updating = useAppSelector(state => state.addresses.updating);
  const updateSuccess = useAppSelector(state => state.addresses.updateSuccess);

  const handleClose = () => {
    navigate('/addresses' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getDarazUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...addressesEntity,
      ...values,
      user: darazUsers.find(it => it.id.toString() === values.user.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...addressesEntity,
          user: addressesEntity?.user?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="darazDemoApp.addresses.home.createOrEditLabel" data-cy="AddressesCreateUpdateHeading">
            Create or edit a Addresses
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="addresses-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Street"
                id="addresses-street"
                name="street"
                data-cy="street"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  maxLength: { value: 30, message: 'This field cannot be longer than 30 characters.' },
                }}
              />
              <ValidatedField
                label="City"
                id="addresses-city"
                name="city"
                data-cy="city"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  maxLength: { value: 30, message: 'This field cannot be longer than 30 characters.' },
                }}
              />
              <ValidatedField
                label="State"
                id="addresses-state"
                name="state"
                data-cy="state"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  maxLength: { value: 30, message: 'This field cannot be longer than 30 characters.' },
                }}
              />
              <ValidatedField id="addresses-user" name="user" data-cy="user" label="User" type="select" required>
                <option value="" key="0" />
                {darazUsers
                  ? darazUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>This field is required.</FormText>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/addresses" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default AddressesUpdate;
